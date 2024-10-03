package com.project.webchiasetailieu.models.entites;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name="Personal_Information")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PerInfo_id")
    private int perInfoId;

    @Column(name="FULL_NAME", nullable=false, length=255)
    private String fullName;

    @Column(name="Birthday", nullable=false)
    private Date birthday;

    @Column(name="Sex", nullable=false)
    private String sex;

    @Column(name="Avatar", nullable=false)
    private String avatar;

    @PrePersist
    public void prePersist() {
        if (this.avatar == null || this.avatar.isEmpty()) {
            this.avatar = "default-avatar.jpg";
        }
    }


    @OneToOne
    @JoinColumn(name="AccountID",  nullable=false)
    private Account account;

}
