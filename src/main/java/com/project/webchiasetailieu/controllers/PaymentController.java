package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.dtos.PaymentDTO;
import com.project.webchiasetailieu.models.entites.Payment;
import com.project.webchiasetailieu.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping()
    public ResponseEntity<List<Payment>> getAllPayment() {
        List<Payment> payment = paymentService.getAllPayments();
        return ResponseEntity.ok(payment);
    }
    @GetMapping("/accPayHis/{AccountId}")
    public ResponseEntity<List<Payment>> getPaymentByAccountId(@PathVariable int AccountId) {
        List<Payment> payments = paymentService.getAllPaymentFromAccount(AccountId);
        return ResponseEntity.ok(payments);
    }

    @PostMapping()
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors.toString());
        }
        Payment payment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(payment);
    }
}


