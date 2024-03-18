package com.nomad.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nomad.main.entity.PartnerSearch;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-18
 */
public interface PartnerSearchService extends IService<PartnerSearch> {

    PartnerSearch findByUserId(Long loginUserId);

}
