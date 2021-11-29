/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.users;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author CND
 */
public class UsersDTO implements Serializable {

    private String email, password, name, status;
    private boolean role;

    public UsersDTO() {
    }

    public UsersDTO(String email, String password, String name, String status, boolean role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.status = status;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

   

}