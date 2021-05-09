package com.kankan.discover.module.school.admin;

import com.google.common.collect.ImmutableMap;
import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.common.SchoolType;
import com.kankan.discover.model.school.School;
import com.kankan.discover.module.school.param.AddSchoolParam;
import com.kankan.discover.module.school.param.UpdateSchoolParam;
import com.kankan.discover.service.SchoolService;
import com.kankan.discover.service.impl.PrivateSchoolService;
import com.kankan.discover.service.impl.PublicSchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@Api(tags = "管理后台-学校管理")
@RestController
@RequestMapping("admin/school/{schoolType}")
public class AdminSchoolController {

  @Autowired
  private PublicSchoolService publicSchoolService;
  @Autowired
  private PrivateSchoolService privateSchoolService;

  @ApiOperation("创建学校")
  @PostMapping("create")
  public CommonResponse createSchool(@PathVariable(value = "schoolType") Integer schoolType,
                                     @RequestBody AddSchoolParam schoolParam) {
    SchoolService schoolService = getSchoolService(schoolType);
    School school = schoolParam.getSchool(schoolType);
    School result = schoolService.addNewSchool(school);
    return CommonResponse.success(result);
  }


  @ApiOperation("学校列表")
  @GetMapping("list")
  public CommonResponse listSchool(
    @PathVariable(value = "schoolType", required = false) Integer schoolType,
    @RequestParam(value = "startIndex", required = false, defaultValue = "1") Integer pageNumer,
    @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {
    SchoolService schoolService = getSchoolService(schoolType);
    List<School> infoList = schoolService.list((pageNumer - 1) * pageSize, pageSize);
    Long totalCount = schoolService.count();
    Integer totalPage = totalCount / pageSize + totalCount % pageSize == 0 ? 0 : 1;
    Map<String, Object> resultMap = ImmutableMap.of("infoList", infoList, "totalCount", totalCount, "totalPage", totalPage);
    return CommonResponse.success(resultMap);
  }


  @ApiOperation("学校详情")
  @GetMapping("detail/{schoolId}")
  public CommonResponse detailSchool(@PathVariable(value = "schoolType") Integer schoolType,
                                     @PathVariable(value = "schoolId") String schoolId) {
    SchoolService schoolService = getSchoolService(schoolType);
    School school = schoolService.detail(schoolId);
    return CommonResponse.success(school);
  }

  @ApiOperation("更新学校信息")
  @PostMapping("update/{schoolId}")
  public CommonResponse updateSchool(
    @PathVariable(value = "schoolType") Integer schoolType,
    @PathVariable(value = "schoolId") String schoolId,
    @RequestBody UpdateSchoolParam updateSchoolParam) {
    SchoolService schoolService = getSchoolService(schoolType);
    School school = updateSchoolParam.getSchool(schoolId, schoolType);
    schoolService.saveSchool(school);
    return  CommonResponse.success();
  }

  @ApiOperation("删除学校")
  @PostMapping("del/{schoolId}")
  public CommonResponse delSchool(
    @PathVariable(value = "schoolType") Integer schoolType,
    @PathVariable(value = "schoolId") String schoolId) {
    SchoolService schoolService = getSchoolService(schoolType);
    schoolService.removeSchool(schoolId);
    return CommonResponse.success();
  }

  private SchoolService getSchoolService(Integer schoolType) {
    SchoolType currentSchoolType = SchoolType.schoolType(schoolType);
    SchoolService schoolService = privateSchoolService;
    if (SchoolType.PUBLIC == currentSchoolType) {
      schoolService = publicSchoolService;
    }
    return schoolService;
  }
}
