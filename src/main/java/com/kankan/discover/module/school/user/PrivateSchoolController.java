package com.kankan.discover.module.school.user;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.model.school.School;
import com.kankan.discover.service.SchoolService;
import com.kankan.discover.service.impl.PrivateSchoolService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "私立学校接口")
@RestController
@RequestMapping("school/private/user")
public class PrivateSchoolController {

    private SchoolService schoolService;

    public PrivateSchoolController(PrivateSchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @ApiOperation("学校列表")
    @GetMapping("list")
    public CommonResponse list(
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
            @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
        List<School> schoolList = schoolService.list(startIndex, limit);
        return CommonResponse.success(schoolList);
    }

    @ApiOperation("附近的学校")
    @GetMapping("nearBy")
    public CommonResponse nearBy(
            @RequestParam(value = "longitude") Double longitude,
            @RequestParam(value = "latitude") Double latitude) {
        List<School> schoolList = schoolService.findNearBy(longitude, latitude);
        return CommonResponse.success(schoolList);
    }

    @ApiOperation("按照地区搜索")
    @GetMapping("findByArea")
    public CommonResponse findByArea(@RequestParam(value = "area") String area) {
        List<School> schoolList = schoolService.findByArea(area);
        return CommonResponse.success(schoolList);
    }

    @ApiOperation("距离优先")
    @GetMapping("sortByDistance")
    public CommonResponse sortByDistance(
            @RequestParam(value = "longitude") Double longitude,
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
            @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
        List<School> schoolList = schoolService.sortByDistance(longitude, latitude, startIndex, limit);
        return CommonResponse.success(schoolList);
    }

    @ApiOperation("热门排序")
    @GetMapping("sortByHot")
    public CommonResponse sortByHot(
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
            @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
        List<School> schoolList = schoolService.sortByHot(startIndex, limit);
        return CommonResponse.success(schoolList);
    }


    @ApiOperation("按照过滤条件搜索")
    @GetMapping("filter")
    public CommonResponse filter(
            @RequestParam(value = "bizScope") Integer bizScope,
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
            @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
        List<School> schoolList = schoolService.filter(bizScope, startIndex, limit);
        return CommonResponse.success(schoolList);
    }

    @ApiOperation("学校详情")
    @GetMapping("detail")
    public CommonResponse detail(@RequestParam(value = "id") String id){
        School school= schoolService.detail(id);
        return CommonResponse.success(school);
    }
}
