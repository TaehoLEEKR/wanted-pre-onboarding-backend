package com.example.wantedpreonboardingbackend.model.entity;

import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AuditOverride(forClass = BaseTimeEntity.class)
public class Community extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;

    @Column(columnDefinition = "NVARCHAR(30) NOT NULL")
    private String communityName;
    @Column(columnDefinition = "TEXT NOT NULL")
    private String content;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

//    public void update(String content, String communityName) {
//        this.content = content;
//        this.communityName = communityName;
//    }
}
