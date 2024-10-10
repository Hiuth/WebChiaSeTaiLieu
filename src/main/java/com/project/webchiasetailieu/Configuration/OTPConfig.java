package com.project.webchiasetailieu.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Cấu hình thời gian tồn tại của mã otp
@Configuration
public class OTPConfig {

    @Value("${otp.expiration}")
    private int otpExpirationTime;

    @Bean
    public int otpExpirationTime() {
        return otpExpirationTime; // Thời gian tồn tại của OTP (phút)
    }
}

