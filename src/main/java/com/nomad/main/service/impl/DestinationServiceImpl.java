package com.nomad.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Destination;
import com.nomad.main.mapper.DestinationMapper;
import com.nomad.main.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**   
 *
 * <p>说明： 目的地服务实现层</P>
 * @version: V1.0
 * @author: alex
 * 
 */
@Slf4j
@Service
public class DestinationServiceImpl  extends ServiceImpl<DestinationMapper, Destination>
    implements DestinationService  {
   @Autowired
   private DestinationMapper destinationMapper;

   @Override
   public List<Destination> getCityNameList() {
      LambdaQueryWrapper<Destination> wrapper = Wrappers.lambdaQuery();
      wrapper.select(Destination::getId).select(Destination::getName);
      return destinationMapper.selectList(wrapper);
   }

   @Override
   public ResultVo create(Destination destination) {
      Date now = new Date();
      destination.setCreatedAt(now);
      destination.setUpdatedAt(now);
      destinationMapper.insert(destination);
      return ResultVo.success(destination);
   }

   @Override
   public ResultVo update(Destination destination) {
      Long id = destination.getId();
      if(Objects.isNull(id)){
         return ResultVo.failed("城市不存在");
      }
      Destination detail = this.getById(id);
      if(Objects.isNull(detail)){
         return ResultVo.failed("城市不存在");
      }
      destinationMapper.updateById(destination);
      return ResultVo.success(destination);
   }
}