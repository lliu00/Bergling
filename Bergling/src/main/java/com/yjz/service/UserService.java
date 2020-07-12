package com.yjz.service;

import com.yjz.model.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User getById(Long id);

    User getByUsername(String username);
}
