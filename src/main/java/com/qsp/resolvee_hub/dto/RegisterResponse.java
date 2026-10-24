package com.qsp.resolvee_hub.dto;

import com.qsp.resolvee_hub.entity.User;
import com.qsp.resolvee_hub.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterResponse {

    private int id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public RegisterResponse(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        phone = user.getPhone();
        role = user.getRoles();
        created_at = user.getCreated_at();
        updated_at = user.getUpdated_at();
    }

}