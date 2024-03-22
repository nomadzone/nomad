package com.nomad.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.PartnerSearch;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-18
 */
public interface PartnerSearchService extends IService<PartnerSearch> {

    PartnerSearch findByUserId(Long id, Long loginUserId);

    List<PartnerSearch> findByUserId2(Long loginUserId);

    List<PartnerSearch> listNkm(Float latitude, Float longitude, int km);

    List<PartnerSearch> search(String key);

    ResultVo<List<PartnerSearch>> getPlayTogether(String locationName);
    ResultVo<List<PartnerSearch>> getPlayTogetherTop2(String locationName);
}
