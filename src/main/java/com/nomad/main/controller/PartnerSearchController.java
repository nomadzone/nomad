package com.nomad.main.controller;


import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.PartnerSearch;
import com.nomad.main.service.PartnerSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        // TODO : 空字符串被允许吗？
        if(partnerSearch.getId() == null) {
            return ResultVo.failed("'id'为空");
        }

        // TODO XXX: login user id
        Long loginUserId = 1L;
        PartnerSearch havePartnerSearch = partnerSearchService.findByUserId(loginUserId);
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
        boolean b = partnerSearchService.removeById(id);
        return ResultVo.success(null);
    }

}

