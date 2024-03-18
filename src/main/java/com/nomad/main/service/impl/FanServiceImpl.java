package com.nomad.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.entity.Fan;
import com.nomad.main.mapper.FanMapper;
import com.nomad.main.service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Service
public class FanServiceImpl extends ServiceImpl<FanMapper, Fan> implements FanService {

    @Autowired
    FanMapper fanMapper;

    public Fan findByUserId(Long userId, Long loginUserId) {
        Fan fan = fanMapper.findByUserId(userId, loginUserId);
        return fan;
    }

}
