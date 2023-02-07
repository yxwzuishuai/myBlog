package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.entity.Link;
import com.sangeng.entity.vo.LinkVo;
import com.sangeng.mapper.LinkMapper;
import com.sangeng.result.Result;
import com.sangeng.service.LinkService;
import com.sangeng.util.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sangeng.constants.Constants.LINK_STATUS_NORMAL;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-01-31 14:05:12
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    ////获取全部的友链
    @Override
    public Result getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, LINK_STATUS_NORMAL);
        List<Link> links = baseMapper.selectList(queryWrapper);

        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return Result.okResult(linkVos);
    }
}

