package com.app.userservice.services;

import java.util.List;

public interface UserService {

    void assignRoles(String userId, List<String> roles);

}