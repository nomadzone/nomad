package com.nomad.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.nomad.main.entity.Destination;

/**   
 *
 * <p>说明： 目的地数据访问层</p>
 * @version: V1.0
 * @author: alex
 * 
 */
@Mapper
public interface DestinationMapper extends BaseMapper<Destination> {
}