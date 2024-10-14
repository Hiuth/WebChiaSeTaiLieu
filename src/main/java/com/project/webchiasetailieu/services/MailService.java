package com.project.webchiasetailieu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OTPService otpService;

    /*public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom("pdfHub5shareDoc@gmail.com");  // Đảm bảo email gửi là từ tài khoản đã cấu hình

        System.out.println("Sending email to: " + to);
        try {
            mailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }  // Thêm dòng này để thực sự gửi email
    }*/

    public void sendOTPMail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("pdfHub5shareDoc@gmail.com");
        message.setSubject("Ma OTP");
        message.setText("Ma dang ky tai khoan pdfHub: " + otpService.generateOTP());

        mailSender.send(message);
    }
}

