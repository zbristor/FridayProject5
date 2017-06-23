package com.example.demo;

import org.springframework.stereotype.Controller;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by student on 6/23/17.
 */
@Controller
public class Users {
    @Id
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Boolean enabled=true;
}
