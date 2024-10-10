package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.Configuration.OTPConfig;
import org.springframework.stereotype.Service;

@Service
public class OTPService {
    private int otpExpirationTime;

    public OTPService(OTPConfig otp){
        this.otpExpirationTime = otp.otpExpirationTime();
    }

    public String generateOTP(){
        return String.valueOf((int)(Math.random()* 1000000 ));
    }

    public void saveOtp(String email, String otp) {
        // Lưu OTP vào database cùng với thời gian hết hạn
    }
}