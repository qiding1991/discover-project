package com.kankan.discover.module.job.admin;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "管理后台-工作管理")
@RestController
@RequestMapping("admin/job")
public class AdminJobController {

  @Autowired
  private JobService jobService;

  @ApiOperation("工作列表")
  @GetMapping("list")
  public CommonResponse listJob(@RequestParam(value = "startIndex", required = false, defaultValue = "1") Integer pageNumer,
                                @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {


  }

  @ApiOperation("工作详情")
  @GetMapping("detail/{jobId}")
  public CommonResponse detailJob(@PathVariable(value = "jobId") String jobId) {

  }


  @ApiOperation("删除工作")
  @PostMapping("delete/{jobId}")
  public CommonResponse delJob(@PathVariable(value = "jobId") String jobId) {

  }


}
