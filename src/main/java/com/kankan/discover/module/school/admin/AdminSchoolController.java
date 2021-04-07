package com.kankan.discover.module.school.admin;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.service.impl.PrivateSchoolService;
import com.kankan.discover.service.impl.PublicSchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "管理后台-学校管理")
@RestController
@RequestMapping("admin/school")
public class AdminSchoolController {

  @Autowired
  private PublicSchoolService publicSchoolService;
  @Autowired
  private PrivateSchoolService privateSchoolService;


  @ApiOperation("创建学校")
  @PostMapping("create")
  public CommonResponse createSchool() {

  }


  @ApiOperation("学校列表")
  @GetMapping("list")
  public CommonResponse listSchool(
    @RequestParam(value = "startIndex", required = false, defaultValue = "1") Integer pageNumer,
    @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize,
    @RequestParam(value = "type", required = false) Integer schoolType) {

  }


  @ApiOperation("学校详情")
  @GetMapping("detail/{schoolId}")
  public CommonResponse detailSchool(@PathVariable(value = "schoolId") String schoolId) {

  }

  @ApiOperation("更新学校信息")
  @PostMapping("update")
  public CommonResponse updateSchool(){

  }



  @ApiOperation("删除学校")
  @PostMapping("del/{schoolId}")
  public CommonResponse delSchool(@PathVariable(value = "schoolId") String schoolId) {

  }


}
