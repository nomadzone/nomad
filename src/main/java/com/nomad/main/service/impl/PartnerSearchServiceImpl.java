package com.nomad.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Destination;
import com.nomad.main.entity.PartnerSearch;
import com.nomad.main.mapper.PartnerSearchMapper;
import com.nomad.main.service.PartnerSearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ResultVo<List<PartnerSearch>> getPlayTogether(String locationName) {
        if(StringUtils.isEmpty(locationName)){
            return ResultVo.failed("位置信息不能为空");
        }
        LambdaQueryWrapper<PartnerSearch> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(PartnerSearch::getLocationName,locationName);
        List<PartnerSearch> searches = partnerSearchMapper.selectList(wrapper);
        return ResultVo.success(searches);
    }

    @Override
    public ResultVo<List<PartnerSearch>> getPlayTogetherTop2(String locationName) {
        // TODO  返回最新的两条
        if(StringUtils.isEmpty(locationName)){
            return ResultVo.failed("位置信息不能为空");
        }
        LambdaQueryWrapper<PartnerSearch> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(PartnerSearch::getLocationName,locationName);
        wrapper.orderByDesc(PartnerSearch::getUpdateAt); // 按 更新时间 降序排序
        wrapper.last("LIMIT 2"); // 返回最新的两条 2
        List<PartnerSearch> searches = partnerSearchMapper.selectList(wrapper);
        return ResultVo.success(searches);
    }
}
