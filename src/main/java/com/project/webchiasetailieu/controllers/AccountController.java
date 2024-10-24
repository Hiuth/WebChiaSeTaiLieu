package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.components.JwtTokenUtils;
import com.project.webchiasetailieu.models.dtos.AccountDTO;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.services.AccountService;
import com.project.webchiasetailieu.services.IAccountService;
import com.project.webchiasetailieu.services.MailService;
import com.project.webchiasetailieu.services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
//@RestController
@RequestMapping("/api/accounts/")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private OTPService otpService;

    @Autowired
    private MailService mailService;

    @GetMapping("/otp")
    public ResponseEntity<String> otp(){
        return ResponseEntity.ok(otpService.generateOTP());
    }

    @GetMapping("/generate-secret-key")
    public ResponseEntity<String> generateSecretKey(){
        return ResponseEntity.ok(jwtTokenUtils.generateSecretKey());

    }

    /*@PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody AccountDTO accountDTO) {
        try {
            mailService.sendMail(accountDTO.getEmail(), "Ma OTP", otpService.generateOTP());
            return ResponseEntity.ok("OTP sent");
        } catch (MailException e) {
            // Nếu có lỗi khi gửi email
            return ResponseEntity.status(500).body("Account registered successfully but failed to send OTP email.");
        }
    }*/

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestBody AccountDTO accountDTO) {
        mailService.sendOTPMail(accountDTO.getEmail());
        return ResponseEntity.ok("OTP sent");
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AccountDTO accountDTO) {
        try {
            Account account = accountService.register(accountDTO);
            return ResponseEntity.ok("Account registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAccountById(@PathVariable Integer id, Model model) {
        try {
            List<Account> accounts = accountService.findAccountById(id);
            if (!accounts.isEmpty()) {
                model.addAttribute("Personal_account", accounts.get(0));
                return ResponseEntity.ok(accounts.get(0));
            } else {
                return ResponseEntity.status(404).body("Account not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public String findAllAccounts(Model model) {
        // Lấy danh sách tài khoản từ service
        List<Account> accounts = accountService.findAllAccounts();

        // Đưa danh sách tài khoản vào model để truyền sang view
        model.addAttribute("accounts", accounts);

        // Trả về tên của trang Thymeleaf
        return "Admin/quanlytaikhoan";
    }

    @PutMapping("/{id}/update-password")
    public ResponseEntity<String> updatePassword(@PathVariable Integer id, @RequestBody Map<String, Object> request) {
        if (request.get("newPassword") == null) {
            return ResponseEntity.status(400).body("newPassword cannot be null");
        }
        String newPassword = (String) request.get("newPassword");
        try {
            Account account = accountService.updatePassword(id, newPassword);
            return ResponseEntity.ok("Password updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Account not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> banOrUnbanAccount(@PathVariable Integer id, @RequestBody Map<String, Object> request) {
        if (request.get("isBanned") == null) {
            return ResponseEntity.status(400).body("isBanned cannot be null");
        }
        boolean isBanned = (boolean) request.get("isBanned");
        AccountService.BanType banType = null;
        if (isBanned) {
            if (request.get("banType") == null) {
                return ResponseEntity.status(400).body("banType cannot be null when banning an account");
            }
            banType = AccountService.BanType.valueOf((String) request.get("banType"));
        }
        try {
            List<Account> accountOptional = accountService.findAccountById(id);
            if (!accountOptional.isEmpty()) {
                Account account = accountOptional.get(0);
                if (isBanned) {
                    if (account.isBanned()) {
                        return ResponseEntity.status(400).body("Account is already banned");
                    }
                    Account bannedAccount = accountService.banAccount(id, banType);
                    return ResponseEntity.ok("Account banned successfully until " + bannedAccount.getBanUntil());
                } else {
                    if (!account.isBanned()) {
                        return ResponseEntity.status(400).body("Account is not banned");
                    }
                    Account unbannedAccount = accountService.unbanAccount(id);
                    return ResponseEntity.ok("Account unbanned successfully");
                }
            } else {
                return ResponseEntity.status(404).body("Account not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Account not found");
        }
    }
}