package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.FeedBackDTO;
import com.project.webchiasetailieu.models.entites.FeedBack;

import java.util.List;

public interface IFeedBackService {
    FeedBack getFeedBackById(int id);

    List<FeedBack> getAccountFeedBack(int AccountId);

    List<FeedBack> getAllFeedBack();

    FeedBack createFeedBack(FeedBackDTO feedBackDTO);

    void DeleteFeedBackById(int id);

    FeedBack updateFeedBack(FeedBackDTO feedBackDTO);
}
