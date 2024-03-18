package com.nomad.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nomad.main.entity.PartnerSearch;
import org.apache.ibatis.annotations.Mapper;

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

    PartnerSearch findByUserId(Long loginUserId);

}
