package com.project.issuetracker.domain.account;

import com.project.issuetracker.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString(exclude = {"password", "picture"})
@Getter
@NoArgsConstructor
@Entity
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String team;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Account(String name, String email, String team, Role role, String password, String picture) {
        this.name = name;
        this.email = email;
        this.team = team;
        this.picture = picture;
        this.role = role;
        this.password = password;
    }

    public Account update(String name, String team) {
        this.name = name;
        this.team = team;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    @Override
    public String toString() {
        return String.format("User: ID - %l\tEmail - %s", id, email);
    }
}
