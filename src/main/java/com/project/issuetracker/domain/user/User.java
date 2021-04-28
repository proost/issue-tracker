package com.project.issuetracker.domain.user;

import com.project.issuetracker.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String team;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String team, Role role) {
        this.name = name;
        this.email = email;
        this.team = team;
        this.role = role;
    }

    public User update(String name, String team) {
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