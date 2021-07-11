package com.kankan.discover.module.school.user;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.model.school.School;
import com.kankan.discover.model.schoolv2.SchoolV2Info;
import com.kankan.discover.service.SchoolService;
import com.kankan.discover.service.SchoolV2InfoService;
import com.kankan.discover.service.impl.PublicSchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Api(tags = "导入学校数据接口")
@RestController
@RequestMapping("school/public/user")
public class SchoolV2Controller {

  @Autowired
  private SchoolV2InfoService schoolV2InfoService;


  @ApiOperation("学校列表")
  @GetMapping("list")
  public CommonResponse list(
      @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
      @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
    List<SchoolV2Info> schoolList = schoolV2InfoService.list(startIndex, limit);
    return CommonResponse.success(schoolList);
  }

  @ApiOperation("附近的学校")
  @GetMapping("nearBy")
  public CommonResponse nearBy(
      @RequestParam(value = "longitude") Double longitude,
      @RequestParam(value = "latitude") Double latitude) {
    List<SchoolV2Info> schoolList = schoolV2InfoService.findNearBy(longitude, latitude);
    return CommonResponse.success(schoolList);
  }

  @ApiOperation("按照地区搜索")
  @GetMapping("findByArea")
  public CommonResponse findByArea(@RequestParam(value = "area") String area) {
    List<SchoolV2Info> schoolList = schoolV2InfoService.findByArea(area);
    return CommonResponse.success(schoolList);
  }

  @ApiOperation("距离优先")
  @GetMapping("sortByDistance")
  public CommonResponse sortByDistance(
      @RequestParam(value = "longitude") Double longitude,
      @RequestParam(value = "latitude") Double latitude,
      @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
      @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
    List<SchoolV2Info> schoolList = schoolV2InfoService.sortByDistance(longitude, latitude, startIndex, limit);
    return CommonResponse.success(schoolList);
  }


  @ApiOperation("课程详情")
  @GetMapping("detail")
  public CommonResponse detail(@RequestParam(value = "id") String id) {
    SchoolV2Info school = schoolV2InfoService.detail(id);
    return CommonResponse.success(school);
  }

}
