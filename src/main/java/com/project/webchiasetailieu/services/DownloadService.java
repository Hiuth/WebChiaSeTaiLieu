package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.DownloadDTO;
import com.project.webchiasetailieu.models.dtos.NotificationsDTO;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.models.entites.Documents;
import com.project.webchiasetailieu.models.entites.Download;
import com.project.webchiasetailieu.models.entites.Notifications;
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
    private final NotificationsService notificationsService;

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
    public boolean checkDownloadBefore(int accountId, int documentId) {
        for (Download download : downloadReposi.findAllDownloadByAccount_AccountId(accountId)) {
            if(download.getAccount().getAccountId() == accountId) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Download createDownload(DownloadDTO downloadDTO) {
        //kiểm tra point có trong tài khoản
        //Nếu có đủ point thì tiếp tục mua, Không đủ thì báo không đủ
        // thêm phần kiểm tra xem coi tài liệu này đã được mua chưa/ chưa thì bắt mua nếu mua, rồi thì bỏ qua bước thanh toán
        // thêm hàm thông báo cho ng đăng khi có ng tải xuống
        //nếu thằng đăng tải lại thì kh cần bắt nó mua
        Account account = accountReposi.findById(downloadDTO.getAccountId())// người mua
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Documents documents = documentsReposi.findById(downloadDTO.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found"));
        boolean isDownload = checkDownloadBefore(account.getAccountId(), documents.getDocId());

        if(account.getAccountId() == downloadDTO.getAccountId() || isDownload) {
            //hàm dowload ở đây
            throw new RuntimeException("You have bought this document before");
        }else{
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
            createNotification1(account_seller.getAccountId());// thông báo có lượt tải
            createNotification2(account.getAccountId(),documents.getDocName(),documents.getPoint());// thông báo trừ điểm
            return downloadReposi.save(download);
        }

    }

    @Override
    public List<Download> getAllDownload() {
        return downloadReposi.findAll();
    }

    @Override
    public void createNotification1(int accountId) {
        NotificationsDTO noti = new NotificationsDTO();
        noti.setAccountId(accountId);
        noti.setTitle("TÀI LIỆU BẠN ĐĂNG ĐÃ CÓ 1 LƯỢT MUA!");
        noti.setContent("TÀI LIỆU ... BẠN ĐĂNG ĐÃ CÓ 1 MUA MỚI! CHÚC MỪNG BẠN!!!!");
        noti.setNotiType("Thông báo");
        notificationsService.createNotification(noti);
    }

    @Override
    public void createNotification2(int accountId,String docName,int point) {
        NotificationsDTO noti = new NotificationsDTO();
        noti.setAccountId(accountId);
        noti.setTitle("THÔNG BÁO MUA BÀI THÀNH CÔNG!");
        noti.setContent("BẠN ĐÃ DÙNG ĐIỂM ĐỂ MUA TÀI LIỆU "+docName+ " với số điểm là: "+point+" điểm");
        noti.setNotiType("Thông báo");
        notificationsService.createNotification(noti);
    }


    @Override
    public Download updateDownload(int DownloadId, DownloadDTO downloadDTO) {
        return null;
    }

    @Override
    public void deleteDownload(int downloadId) {

    }
}
