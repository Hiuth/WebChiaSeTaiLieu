package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountReposi extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);// tìm tài khoản theo email

    boolean existsByEmail(String email); //kiểm tra xem tài khoản có tồn tại không. Dành cho lúc tạo tài khoản
}
