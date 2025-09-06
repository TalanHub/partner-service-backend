package com.zjl.partnerservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.partnerservice.mapper.TagMapper;
import com.zjl.partnerservice.model.domain.Tag;
import com.zjl.partnerservice.service.TagService;

import org.springframework.stereotype.Service;

/**
 * @author zhenglao
 * @description 针对表【tag(标签表)】的数据库操作Service实现
 * @createDate 2025-08-30 23:11:25
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

}




