package com.ksolves.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_user_info")
public class UserInfo {

    @Id
    @GeneratedValue
    @Column(name = "u_Id")
    private Long id;
    @Column(name = "u_Name")
    private String name;
    @Column(name = "u_Password")
    private String password;
//    @Column(name = "u_Role")
//    private Role role;
    @Column(name = "u_Roles")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    @Column(name = "u_isUserLocked", columnDefinition = "boolean default false")
    private Boolean isUserLocked;
}
