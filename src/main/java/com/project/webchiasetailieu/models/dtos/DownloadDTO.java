package com.project.webchiasetailieu.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadDTO {
    private int paymentId;
    private int accountId;//id của acc mua
    private int documentId;

}
