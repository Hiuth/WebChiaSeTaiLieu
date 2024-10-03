package com.project.webchiasetailieu.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerInfoDTO {
    private int perInfoId;
    private String fullName;
    private Date birthday;
    private String sex;
    private String avatar;
}