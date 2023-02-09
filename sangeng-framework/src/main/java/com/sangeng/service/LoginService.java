package com.sangeng.service;

import com.sangeng.entity.User;
import com.sangeng.result.Result;

public interface LoginService {

    Result login(User user);

    Result logout();
}
