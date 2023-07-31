package com.example.wantedpreonboardingbackend.model.entity;

import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseTimeEntity.class)
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(columnDefinition = "NVARCHAR(255) NOT NULL")
    private String email;

    @Column(columnDefinition = "NVARCHAR(255) NOT NULL")
    private String password;
    @Column(columnDefinition = "NVARCHAR(255) NOT NULL")
    private String userName;

    @Column(columnDefinition = "NVARCHAR(100) NULL")
    private String phone;

}
