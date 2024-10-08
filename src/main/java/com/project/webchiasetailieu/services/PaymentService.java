package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.PaymentDTO;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.models.entites.Documents;
import com.project.webchiasetailieu.models.entites.Payment;
import com.project.webchiasetailieu.repositories.AccountReposi;
import com.project.webchiasetailieu.repositories.DocumentsReposi;
import com.project.webchiasetailieu.repositories.PaymentReposi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentReposi paymentReposi;
    private final DocumentsReposi documentsReposi;
    private final AccountService accountService;
    private final AccountReposi accountReposi;

    @Override
    public Payment getPayment(int id) {
        return paymentReposi.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public List<Payment> getAllPaymentFromAccount(int accountId) {
        return paymentReposi.findAllPaymentByAccount_AccountId(accountId);
    }

    @Override
    public Payment createPayment(PaymentDTO paymentDTO) {
        //kiểm tra point có trong tài khoản
        //Nếu có đủ point thì tiếp tục mua, Không đủ thì báo không đủ
        Account account = accountReposi.findById(paymentDTO.getAccountId())// người mua
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Documents documents = documentsReposi.findById(paymentDTO.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found"));
        if(account.getWalletPoint() < documents.getPoint()){
            throw new RuntimeException("You have not enough wallet point");
        }
        Payment payment = Payment.builder()
                .point(documents.getPoint())
                .account(account)
                .documents(documents)
                .build();
        Account account_seller = accountReposi.findById(documents.getAccountId().getAccountId())
                .orElseThrow(()-> new RuntimeException("Account not found"));
        // tăng điểm cho người bán
        // lấy diem hien tai cua ng ban + cho diem cua tai lieu
        accountService.updateWalletPoint(account_seller.getAccountId(),
                account_seller.getWalletPoint()+documents.getPoint());

        // giảm điểm của người mua
        // lay diem cua ng mua - cho so diem cua tai lieu
        accountService.updateWalletPoint(account.getAccountId(),
                account.getWalletPoint()-documents.getPoint());

        return paymentReposi.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentReposi.findAll();
    }

    @Override
    public Payment updatePayment(int paymentId, PaymentDTO payment) {
        return null;
    }

    @Override
    public void deletePayment(int paymentId) {

    }
}
