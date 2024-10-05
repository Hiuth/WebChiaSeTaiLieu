package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.PaymentDTO;
import com.project.webchiasetailieu.models.entites.Payment;

import java.util.List;

public interface IPaymentService {
    Payment getPayment(int id);

    List<Payment> getAllPaymentFromAccount(int accountId);

    Payment createPayment(PaymentDTO paymentDTO);

    Payment updatePayment(int paymentId, PaymentDTO payment);

    void deletePayment(int paymentId);
}
