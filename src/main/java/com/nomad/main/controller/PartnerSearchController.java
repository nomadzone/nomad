package com.nomad.main.controller;


import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Destination;
import com.nomad.main.entity.PartnerSearch;
import com.nomad.main.service.PartnerSearchService;
import com.nomad.main.utils.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-18
 */
@Tag(name = "PartnerSearch", description = "PartnerSearch APIs")
@RestController
@RequestMapping("/api/v1/partnerSearch")
public class PartnerSearchController {

    @Autowired
    PartnerSearchService partnerSearchService;

    @Operation(summary = "搭子-新增", description = "")
    @PostMapping()
    public ResultVo create(@RequestBody PartnerSearch partnerSearch) {

        // TODO : 空字符串被允许吗？
        if(partnerSearch.getLongitude() == null) {
            return ResultVo.failed("'longitude'不能为空");
        }
        if(partnerSearch.getLatitude() == null) {
            return ResultVo.failed("'latitude'不能为空");
        }
        if(partnerSearch.getLocationName() == null) {
            return ResultVo.failed("'locationName'不能为空");
        }
        if(partnerSearch.getTitle() == null) {
            return ResultVo.failed("'title'不能为空");
        }
        if(partnerSearch.getContent() == null) {
            return ResultVo.failed("'content'不能为空");
        }

        partnerSearch.setId(null);
        // TODO XXX: login user id
        Long loginUserId = 1L;
        partnerSearch.setIntiatorId(loginUserId);
        partnerSearch.setCreatedAt(System.currentTimeMillis());
        partnerSearch.setUpdateAt(System.currentTimeMillis());

        boolean save = partnerSearchService.save(partnerSearch);
        return ResultVo.success(null);

    }

    @Operation(summary = "搭子-修改", description = "")
    @PutMapping()
    public ResultVo update(@RequestBody PartnerSearch partnerSearch) {

        if(partnerSearch.getId() == null) {
            return ResultVo.failed("'id'为空");
        }

        Long loginUserId = AuthUtil.getLoginUserId();
        PartnerSearch havePartnerSearch = partnerSearchService.findByUserId(partnerSearch.getId(), loginUserId);
        if(havePartnerSearch == null) {
            return ResultVo.failed("不能修改他人发布的信息");
        }
        partnerSearch.setUpdateAt(System.currentTimeMillis());
        boolean b = partnerSearchService.updateById(partnerSearch);
        return ResultVo.success(null);

    }

    @Operation(summary = "搭子-查看", description = "")
    @GetMapping("/{id}")
    public ResultVo<PartnerSearch> getById(@PathVariable Long id) {
        PartnerSearch partnerSearch = partnerSearchService.getById(id);
        if(partnerSearch == null) {
            return ResultVo.failed("搭子信息不存在");
        }
        return ResultVo.success(partnerSearch);
    }

    @Operation(summary = "搭子-删除", description = "")
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable Long id) {

        if(id == null) {
            return ResultVo.failed("'id'不能为空");
        }

        Long loginUserId = AuthUtil.getLoginUserId();
        PartnerSearch havePartnerSearch = partnerSearchService.findByUserId(id, loginUserId);
        if(havePartnerSearch == null) {
            return ResultVo.failed("不能删除他人发布的信息");
        }

        boolean b = partnerSearchService.removeById(id);
        return ResultVo.success(null);
    }

    @Operation(summary = "一起玩(更多)-通过位置获取", description = "")
    @GetMapping("/playTogether/{locationName}")
    public ResultVo<List<PartnerSearch>> getPlayTogether(@PathVariable("locationName")String locationName){
       return partnerSearchService.getPlayTogether(locationName);
    }

    @Operation(summary = "一起玩(前两个)-通过位置获取", description = "")
    @GetMapping("/playTogetherTop2/{locationName}")
    public ResultVo<List<PartnerSearch>> getPlayTogetherTop2(@PathVariable("locationName")String locationName){
        return partnerSearchService.getPlayTogetherTop2(locationName);
    }

    @Operation(summary = "标记-新增", description = "")
    @PostMapping("/mark")
    public ResultVo markCreate(@RequestBody PartnerSearch partnerSearch) {

        // TODO : 空字符串被允许吗？
        if(partnerSearch.getLongitude() == null) {
            return ResultVo.failed("'longitude'不能为空");
        }
        if(partnerSearch.getLatitude() == null) {
            return ResultVo.failed("'latitude'不能为空");
        }
        if(partnerSearch.getLocationName() == null) {
            return ResultVo.failed("'locationName'不能为空");
        }
        if(partnerSearch.getTitle() == null) {
            return ResultVo.failed("'title'不能为空");
        }
        if(partnerSearch.getContent() == null) {
            return ResultVo.failed("'content'不能为空");
        }

        partnerSearch.setId(null);
        // TODO XXX: login user id
        Long loginUserId = 1L;
        partnerSearch.setIntiatorId(loginUserId);
        partnerSearch.setCreatedAt(System.currentTimeMillis());
        partnerSearch.setUpdateAt(System.currentTimeMillis());
        partnerSearch.setType("2");//表示标记
        boolean save = partnerSearchService.save(partnerSearch);
        return ResultVo.success(null);

    }

    @Operation(summary = "搭子-活动列表", description = "参数经纬度是当前用户的经纬度，该接口返回10km之内的数据。(使用Sphere坐标系计算)")
    @GetMapping("/list/{latitude}/{longitude}")
    public ResultVo<List<PartnerSearch>> list(@PathVariable Float latitude, @PathVariable Float longitude) {
        List<PartnerSearch> reList = partnerSearchService.listNkm(latitude, longitude, 15);
        return ResultVo.success(reList);
    }

    @Operation(summary = "搭子-地图信息", description = "分组方式, 按照地点名称进行分组。参数经纬度是当前用户的经纬度，该接口返回10km之内的数据。(使用Sphere坐标系计算)")
    @GetMapping("/mapinfo/{latitude}/{longitude}")
    public ResultVo<Map<String, List<PartnerSearch>>> mapInfo(@PathVariable Float latitude, @PathVariable Float longitude) {
        List<PartnerSearch> list = partnerSearchService.listNkm(latitude, longitude, 15);
        // group by location name.
        Map<String, List<PartnerSearch>> map = new HashMap<>();
        for(PartnerSearch partnerSearch : list) {
            String locationName = partnerSearch.getLocationName();
            List<PartnerSearch> mapList = map.get(locationName);
            if(mapList == null) {
                mapList = new ArrayList<>();
            }
            mapList.add(partnerSearch);
            map.put(locationName, mapList);
        }
        return ResultVo.success(map);

    }

    @Operation(summary = "搭子-活动搜索", description = "返回list包含：参数模糊匹配到位置名称、标题名、内容的数据")
    @GetMapping("/search/{key}")
    public ResultVo<List<PartnerSearch>> search(@PathVariable String key) {

        List<PartnerSearch> reList = partnerSearchService.search(key);
        return ResultVo.success(reList);
    }

}

