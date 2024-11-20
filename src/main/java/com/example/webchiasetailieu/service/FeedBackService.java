package com.example.webchiasetailieu.service;

import com.example.webchiasetailieu.dto.request.FeedBackRequest;
import com.example.webchiasetailieu.dto.request.UpdateFeedbackRequest;
import com.example.webchiasetailieu.dto.response.FeedBackResponse;
import com.example.webchiasetailieu.entity.Account;
import com.example.webchiasetailieu.entity.Feedbacks;
import com.example.webchiasetailieu.enums.StatusFeedbackType;
import com.example.webchiasetailieu.exception.AppException;
import com.example.webchiasetailieu.exception.ErrorCode;
import com.example.webchiasetailieu.repository.AccountRepository;
import com.example.webchiasetailieu.repository.DocumentRepository;
import com.example.webchiasetailieu.repository.FeedBackRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FeedBackService {
    FeedBackRepository repository;
    AccountRepository accountRepository;
    private final DocumentRepository documentRepository;

    @PreAuthorize("hasRole('USER')")
    public FeedBackResponse createFeedback(FeedBackRequest request) {
        Account account = getAccountFromAuthentication();
        Feedbacks feedbacks = Feedbacks.builder()
                .feedback(request.getFeedback())
                .type(request.getFeedbackType().toString())
                .status(StatusFeedbackType.UNPROCESSED.getDescription())
                .account(account)
                .otherId(request.getOtherId())
                .build();
        return convertToResponse(repository.save(feedbacks));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public FeedBackResponse updateFeedback(UpdateFeedbackRequest request) {
        Feedbacks feedbacks = repository.findById(request.getId()).orElseThrow(
                () -> new AppException(ErrorCode.FEEDBACK_NOT_FOUND));
        if(request.getStatus() != null)
            feedbacks.setStatus(request.getStatus());
        if(request.getResponseFromAdmin()!= null)
            feedbacks.setFeedbackFromAdmin(request.getResponseFromAdmin());
        return convertToResponse(repository.save(feedbacks));
    }

    @PreAuthorize("hasRole('USER')")
    public List<Feedbacks> getMyFeedbacks() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        Account account = accountRepository.findById(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return repository.findAllByAccount_Id(account.getId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<Feedbacks> getAll() {
        if(repository.findAll().isEmpty())
            throw new AppException(ErrorCode.LIST_EMPTY);
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public FeedBackResponse getById(String id) {
        return convertToResponse(repository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOTIFICATION_NOT_EXISTED)));
    }

    @PreAuthorize("hasAuthority('DELETE_FEEDBACK')")
    public String deleteById(String id) {
        repository.deleteById(id);
        return "Deleted";
    }

    private FeedBackResponse convertToResponse(Feedbacks feedbacks) {
        return FeedBackResponse.builder()
                .email(feedbacks.getAccount().getEmail())
                .feedback(feedbacks.getFeedback())
                .date(feedbacks.getDate())
                .type(feedbacks.getType())
                .status(feedbacks.getStatus())
                .feedbackFromAdmin(feedbacks.getFeedbackFromAdmin())
                .build();
    }

    private Account getAccountFromAuthentication() {
        return accountRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}
