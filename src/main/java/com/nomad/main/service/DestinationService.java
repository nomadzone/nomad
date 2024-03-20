package com.nomad.main.service;

import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Destination;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**   
 *
 * <p>说明： 目的地服务层</P>
 * @version: V1.0
 * @author: alex
 * 
 */
public interface DestinationService extends IService<Destination> {

    /**
     * 获取城市名称
     * @return
     */
    public List<Destination> getCityNameList();

    public ResultVo create(Destination destination);
    public ResultVo update(Destination destination);
}