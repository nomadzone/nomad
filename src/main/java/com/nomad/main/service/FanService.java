package com.nomad.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nomad.main.entity.Fan;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
public interface FanService extends IService<Fan> {

    Fan findByUserId(Long userId, Long loginUserId);

}
