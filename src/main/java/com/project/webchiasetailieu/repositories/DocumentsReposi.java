package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface DocumentsReposi extends JpaRepository<Documents, Integer> {
}
