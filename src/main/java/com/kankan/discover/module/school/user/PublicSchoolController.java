package com.kankan.discover.module.school.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.model.school.School;
import com.kankan.discover.service.SchoolService;
import com.kankan.discover.service.impl.PublicSchoolService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "公立学校接口")
@RestController
@RequestMapping("school/public/user")
public class PublicSchoolController {

    private SchoolService schoolService;

    public PublicSchoolController(PublicSchoolService schoolService) {
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

    @ApiOperation("菲莎排名")
    @GetMapping("sortByFeiSha")
    public CommonResponse sortByFeiSha(
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
            @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
        List<School> schoolList = schoolService.sortByFeiSha(startIndex, limit);
        return CommonResponse.success(schoolList);
    }


    @ApiOperation("按照过滤条件搜索")
    @GetMapping("filter")
    public CommonResponse filter(
            @RequestParam(value = "bizScope") Integer bizScope,
            @RequestParam(value = "language") String language,
            @RequestParam(value = "proj") String proj,
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
            @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
        List<School> schoolList = schoolService.filter(bizScope, language, proj, startIndex, limit);
        return CommonResponse.success(schoolList);
    }

    @ApiOperation("课程详情")
    @GetMapping("detail")
    public CommonResponse detail(@RequestParam(value = "id") String id){
        School school= schoolService.detail(id);
        return CommonResponse.success(school);
    }

}
