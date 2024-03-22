package com.nomad.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nomad.main.entity.PartnerSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-18
 */
@Mapper
public interface PartnerSearchMapper extends BaseMapper<PartnerSearch> {

    PartnerSearch findByUserId(Long id, Long loginUserId);

    List<PartnerSearch> findByUserId2(Long loginUserId);

    List<PartnerSearch> search(String key);

}
