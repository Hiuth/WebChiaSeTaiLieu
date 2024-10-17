package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.FeedBackDTO;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.models.entites.DocCategory;
import com.project.webchiasetailieu.models.entites.FeedBack;
import com.project.webchiasetailieu.repositories.AccountReposi;
import com.project.webchiasetailieu.repositories.FeedBackReposi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedBackService implements IFeedBackService {
   private final FeedBackReposi feedBackReposi;
    private final AccountReposi accountReposi;

    @Override
    public FeedBack getFeedBackById(int id) {
        return feedBackReposi.findById(id)
                .orElseThrow(()-> new RuntimeException("FeedBack not found"));
    }

    @Override
    public List<FeedBack> getAccountFeedBack(int AccountId) {
        List<FeedBack> feedBacks = feedBackReposi.findByAccount_AccountId(AccountId);
        if(feedBacks.isEmpty()){
            throw new RuntimeException("This account does not have any feedback");
        }
        return feedBacks;
    }


    @Override
    public List<FeedBack> getAllFeedBack() {
        return feedBackReposi.findAll();
    }

    @Override
    public FeedBack createFeedBack(FeedBackDTO feedBackDTO) {
        Account account = accountReposi.findById(feedBackDTO.getAccountId())
                .orElseThrow(()-> new RuntimeException("Account not found"));
        FeedBack feedBack = FeedBack.builder()
                .feedText(feedBackDTO.getFeedBackText())
                .feedType(feedBackDTO.getFeedBackType())
                .feedStatus("Chưa xử lý")
                .account(account)
                .build();
        return feedBackReposi.save(feedBack);
    }

    @Override
    public void DeleteFeedBackById(int id) {
        feedBackReposi.deleteById(id);
    }

    @Override
    public FeedBack updateFeedBack(int feedID, FeedBackDTO feedBackDTO) {
       FeedBack  existingFeedBack = getFeedBackById(feedID);
       existingFeedBack.setFeedStatus(feedBackDTO.getFeedBackStatus());
       existingFeedBack.setAdminFeedback(feedBackDTO.getAdminFeedBack());
       feedBackReposi.save(existingFeedBack);
        return existingFeedBack;
    }
}
