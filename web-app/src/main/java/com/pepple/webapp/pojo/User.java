package com.pepple.webapp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/10 17:52
 */
@Entity
@Table(name="user_info")
public class User implements Serializable{
    private int id;
    private String name;
    private String password;
    @Id
    @GenericGenerator(name = "id",strategy = "identity")
    @GeneratedValue(generator = "id")
    @Column(name = "id",unique = true,nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
