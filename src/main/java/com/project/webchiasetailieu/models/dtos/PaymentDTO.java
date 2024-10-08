package com.project.webchiasetailieu.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private int paymentID;
    private int Point;
    private int accountID;
    private int documentId;

}
