package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.PerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerInfoReposi extends JpaRepository<PerInfo, Integer> {

    Optional<PerInfo> findById(Integer id);

}