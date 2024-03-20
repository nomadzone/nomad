package com.nomad.main.controller;


import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Destination;
import com.nomad.main.entity.Fan;
import com.nomad.main.entity.PartnerSearch;
import com.nomad.main.entity.Posts;
import com.nomad.main.service.DestinationService;
import com.nomad.main.service.FanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  目的地-城市
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Tag(name = "Destination", description = "城市 APIs")
@RestController
@RequestMapping("/api/v1/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @Operation(summary = "获取城市名称列表", description = "")
    @GetMapping("/getCityName")
    public ResultVo<List<Destination>> getCityNames() {
        List<Destination> citys = destinationService.getCityNameList();
        return ResultVo.success(citys);

    }

    @Operation(summary = "城市-新增", description = "")
    @PostMapping()
    public ResultVo create(@RequestBody @Valid Destination destination){
        return destinationService.create(destination);
    }

    @Operation(summary = "城市-修改", description = "")
    @PutMapping()
    public ResultVo update(@RequestBody Destination destination){
        return destinationService.update(destination);
    }


}

