package com.project.webchiasetailieu.models.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name="Personal_Information")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PerInfo_id ")
    private int perInfoId;

    @Column(name="Name",nullable=false,length=255 )
    private String name;

    @Column(name="Birthday",nullable=false )
    private Date birthday;

    @Column(name="Sex", nullable=false )
    private String sex;

    @Column(name="Avatar",nullable=false )
    private String avatar;

    @OneToOne
    @JoinColumn(name="AccountID",nullable=false)
    private Account account;
}
