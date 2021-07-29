package com.kankan.discover.module.school.admin;

import com.google.common.collect.ImmutableMap;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.common.ErrorCode;
import com.kankan.discover.common.SchoolType;
import com.kankan.discover.model.school.School;
import com.kankan.discover.model.schoolv2.SchoolV2Info;
import com.kankan.discover.model.schoolv2.SchoolV2Info.SchoolEQAOInfo;
import com.kankan.discover.module.school.param.AddSchoolParam;
import com.kankan.discover.module.school.param.UpdateSchoolParam;
import com.kankan.discover.service.SchoolService;
import com.kankan.discover.service.SchoolV2InfoService;
import com.kankan.discover.service.impl.PrivateSchoolService;
import com.kankan.discover.service.impl.PublicSchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@Api(tags = "管理后台-学校管理")
@RestController
@RequestMapping("admin/school/v2")
public class AdminSchoolV2Controller {

  @Autowired
  private SchoolV2InfoService schoolV2InfoService;

  @ApiOperation("录入全量学校")
  @PostMapping("uploadSchool")
  public CommonResponse createSchool(@RequestParam(value = "school") MultipartFile schoolFile) {

    try (InputStream inputStream = schoolFile.getInputStream()) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String title = reader.readLine();
      List<String> header = Arrays.stream(title.split(",")).map(this::nameToCamel).collect(Collectors.toList());

      String content = "";
      while ((content = reader.readLine()) != null) {
        List<String> contents = Arrays.stream(content.split(",")).map((str) -> str.trim()).collect(Collectors.toList());
        SchoolV2Info school = generateSchool(header, contents);
        schoolV2InfoService.saveSchool(school);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return CommonResponse.success();
  }

  @ApiOperation("录入eqAo")
  @PostMapping("uploadSchoolEQAO")
  public CommonResponse uploadSchoolEQAO(@RequestParam(value = "EQAO") MultipartFile schoolFile) {
    try (InputStream inputStream = schoolFile.getInputStream()) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String title = reader.readLine();
      List<String> header = Arrays.stream(title.split(",")).map(this::nameToCamel).collect(Collectors.toList());
      String content = "";
      while ((content = reader.readLine()) != null) {
        List<String> contents = Arrays.stream(content.split(",")).map((str) -> str.trim()).collect(Collectors.toList());
        SchoolEQAOInfo eqaoInfo = generateEQAO(header, contents);
        schoolV2InfoService.addEQAO(eqaoInfo);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return CommonResponse.success();
  }

  private SchoolEQAOInfo generateEQAO(List<String> header, List<String> contents) {
    Map<String, String> schoolMap = new HashMap<>();
    Gson gson = new Gson();
    for (int i = 0; i < header.size(); i++) {
      if (i < contents.size()) {
        schoolMap.put(header.get(i), contents.get(i));
      }
    }
    SchoolEQAOInfo eqaoInfo = gson.fromJson(gson.toJson(schoolMap), SchoolEQAOInfo.class);
    return eqaoInfo;
  }


  private SchoolV2Info generateSchool(List<String> header, List<String> contents) {
    Map<String, String> schoolMap = new HashMap<>();
    Gson gson = new Gson();
    for (int i = 0; i < header.size(); i++) {
      if (i < contents.size()) {
        schoolMap.put(header.get(i), contents.get(i));
      }
    }
    SchoolV2Info schoolV2Info = gson.fromJson(gson.toJson(schoolMap), SchoolV2Info.class);
    schoolV2Info.setLocation(new Point(Double.valueOf(schoolMap.get("geoLat")), Double.valueOf(schoolMap.get("geoLng"))));
    return schoolV2Info;
  }

  @ApiOperation("学校列表")
  @GetMapping("list")
  public CommonResponse listSchool(
      @RequestParam(value = "startIndex", required = false, defaultValue = "1") Integer pageNumer,
      @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {
    List<SchoolV2Info> infoList = schoolV2InfoService.list((pageNumer - 1) * pageSize, pageSize);
    Long totalCount = schoolV2InfoService.count();
    Integer totalPage = totalCount / pageSize + totalCount % pageSize == 0 ? 0 : 1;
    Map<String, Object> resultMap = ImmutableMap.of("infoList", infoList, "totalCount", totalCount, "totalPage", totalPage);
    return CommonResponse.success(resultMap);
  }


  @ApiOperation("学校详情")
  @GetMapping("detail/{schoolId}")
  public CommonResponse detailSchool(@PathVariable(value = "schoolId") String schoolId) {
    SchoolV2Info school = schoolV2InfoService.detail(schoolId);
    return CommonResponse.success(school);
  }

  @ApiOperation("更新学校信息")
  @PostMapping("update/{schoolId}")
  public CommonResponse updateSchool(
      @PathVariable(value = "schoolId") String schoolId,
      @RequestBody UpdateSchoolParam updateSchoolParam) {
    return CommonResponse.error(ErrorCode.NOT_SUPPORTED);
  }

  @ApiOperation("删除学校")
  @PostMapping("del/{schoolId}")
  public CommonResponse delSchool(@PathVariable(value = "schoolId") String schoolId) {
    schoolV2InfoService.removeSchool(schoolId);
    return CommonResponse.success();
  }

  private String nameToCamel(String title) {
    Function<String, String> firstWordLowCase = (str) -> str.substring(0, 1).toLowerCase() + str.substring(1);
    Function<String, String> firstWordUpper = (str) -> str.substring(0, 1).toUpperCase() + str.substring(1);
    title = Arrays.stream(title.split("_")).map(firstWordUpper).collect(Collectors.joining());
    title = firstWordLowCase.apply(title);
    return title;
  }
}
