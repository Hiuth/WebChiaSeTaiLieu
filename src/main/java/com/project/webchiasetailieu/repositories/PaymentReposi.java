package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentReposi extends JpaRepository<Payment, Integer> {
    List<Payment> findAllPaymentByAccount_AccountId(int accountId);
}
