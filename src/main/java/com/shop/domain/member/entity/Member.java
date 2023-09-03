package com.shop.domain.member.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import com.shop.domain.mart.entity.Mart;
import com.shop.global.audit.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    @OrderBy("createdAt")
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Mart> marts;

    @Getter
    public enum Role {
        SELLER("ROLE_SELLER", "ROLE_USER"),
        USER("ROLE_USER");

        private final String[] roles;

        Role(String... roles) {
            this.roles = roles;
        }
    }
}
