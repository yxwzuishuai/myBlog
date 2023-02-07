package com.sangeng.service;

import com.sangeng.entity.User;
import com.sangeng.result.Result;

public interface BlogLoginService {


    Result login(User user);

    Result logout();
}
