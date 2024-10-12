package com.project.webchiasetailieu.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackDTO {
    private int feedBackId;
    private String feedBackText;
    private String feedBackType;
    private int accountId; //acount up bai
}
