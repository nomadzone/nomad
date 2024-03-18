package com.nomad.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.entity.PartnerSearch;
import com.nomad.main.mapper.PartnerSearchMapper;
import com.nomad.main.service.PartnerSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-18
 */
@Service
public class PartnerSearchServiceImpl extends ServiceImpl<PartnerSearchMapper, PartnerSearch> implements PartnerSearchService {

    @Autowired
    PartnerSearchMapper partnerSearchMapper;

    public PartnerSearch findByUserId(Long loginUserId) {
        return partnerSearchMapper.findByUserId(loginUserId);
    }

}
