package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.FeedBack;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedBackReposi extends JpaRepository<FeedBack, Integer> {
    List<FeedBack> findByAccount_AccountId(int accountId);
}
