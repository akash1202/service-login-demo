package com.ibm.service.login.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

}
