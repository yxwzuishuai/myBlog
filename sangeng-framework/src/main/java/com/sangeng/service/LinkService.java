package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.entity.Link;
import com.sangeng.result.Result;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-01-31 14:05:12
 */
public interface LinkService extends IService<Link> {

    //获取全部的友链
    Result getAllLink();
}

