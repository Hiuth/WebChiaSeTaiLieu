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
    private AccountReposi accountReposi;

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
        Account account = accountReposi.findById(paymentDTO.getAccountID())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Documents documents = documentsReposi.findById(paymentDTO.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found"));
        if(account.getWalletPoint() < documents.getPoint()){
            throw new RuntimeException("You have not enough wallet point");
        }

        return null;
    }

    @Override
    public Payment updatePayment(int paymentId, PaymentDTO payment) {
        return null;
    }

    @Override
    public void deletePayment(int paymentId) {

    }
}
