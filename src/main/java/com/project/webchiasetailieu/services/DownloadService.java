package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.DownloadDTO;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.models.entites.Documents;
import com.project.webchiasetailieu.models.entites.Download;
import com.project.webchiasetailieu.repositories.AccountReposi;
import com.project.webchiasetailieu.repositories.DocumentsReposi;
import com.project.webchiasetailieu.repositories.DownloadReposi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DownloadService implements IDownloadService {

    private final DownloadReposi downloadReposi;
    private final DocumentsReposi documentsReposi;
    private final AccountService accountService;
    private final AccountReposi accountReposi;

    @Override
    public Download getDownload(int id) {
        return downloadReposi.findById(id)
                .orElseThrow(() -> new RuntimeException("Download history not found"));
    }

    @Override
    public List<Download> getAllDownloadFromAccount(int accountId) {
        return downloadReposi.findAllDownloadByAccount_AccountId(accountId);
    }

    @Override
    public Download createDownload(DownloadDTO downloadDTO) {
        //kiểm tra point có trong tài khoản
        //Nếu có đủ point thì tiếp tục mua, Không đủ thì báo không đủ
        // thêm phần kiểm tra xem coi tài liệu này đã được mua chưa/ chưa thì bắt mua nếu mua, rồi thì bỏ qua bước thanh toán
        Account account = accountReposi.findById(downloadDTO.getAccountId())// người mua
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Documents documents = documentsReposi.findById(downloadDTO.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found"));
        if(account.getWalletPoint() < documents.getPoint()){
            throw new RuntimeException("You have not enough wallet point");
        }
        Download download = Download.builder()
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

        return downloadReposi.save(download);
    }

    @Override
    public List<Download> getAllDownload() {
        return downloadReposi.findAll();
    }

    @Override
    public Download updateDownload(int DownloadId, DownloadDTO downloadDTO) {
        return null;
    }

    @Override
    public void deleteDownload(int downloadId) {

    }
}
