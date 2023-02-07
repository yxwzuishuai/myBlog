package com.sangeng.service;

import com.sangeng.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.result.Result;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author Bing
 * @since 2022-02-07
 */
public interface CategoryService extends IService<Category> {


    /**
     * 门户网站 - 查询分类列表
     * @return
     */
    Result getCategoryList();
}
