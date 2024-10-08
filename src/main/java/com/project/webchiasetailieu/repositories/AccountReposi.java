package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountReposi extends JpaRepository<Account, Integer> {
    @Query("select acc from Account  acc where acc.email=:Email" )
    Account findByEmail(@Param("Email") String Email);// tìm tài khoản theo email
    //Account exitsBtName(String name);
    boolean existsByName(String name); //kiểm tra xem tài khoản có tồn tại không. Dành cho lúc tạo tài khoản

    //Account findByAccountId(int id);
    boolean existsByEmail(String email); //kiểm tra xem tài khoản có tồn tại không. Dành cho lúc tạo tài khoản


    List<Account> findAll(); // Find all accounts
}