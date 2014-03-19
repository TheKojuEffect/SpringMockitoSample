package io.koju.sample.directory.entity;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Kapil Koju on 3/19/14.
 */
@Entity
public class User extends AbstractEntity {

    @NotEmpty
    @NotNull
    private String name;

    @Column(unique = true, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
