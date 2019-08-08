package com.mysecurity.demo.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private long id;
    @Column
    private String userName;
    private String password;
    private String roles;

    public User(){}
    public User(String userName){
        this.userName=userName;
    }
}
