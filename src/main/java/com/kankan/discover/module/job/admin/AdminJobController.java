package com.kankan.discover.module.job.admin;

import com.google.common.collect.ImmutableMap;
import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.model.job.Job;
import com.kankan.discover.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "管理后台-工作管理")
@RestController
@RequestMapping("admin/job")
public class AdminJobController {

  @Autowired
  private JobService jobService;

  @ApiOperation("工作列表")
  @GetMapping("list")
  public CommonResponse listJob(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumer,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

    List<Job> jobList = jobService.find((pageNumer - 1) * pageSize, pageSize);
    Long totalCount = jobService.count();
    Long totalPage = totalCount / pageSize + (totalCount % pageSize == 0 ? 0L : 1L);
    Map<String, Object> jobMap = ImmutableMap.of("infoList", jobList, "totalCount", totalCount, "totalPage", totalPage);
    return CommonResponse.success(jobMap);

  }

  @ApiOperation("工作详情")
  @GetMapping("detail/{jobId}")
  public CommonResponse detailJob(@PathVariable(value = "jobId") String jobId) {
    Job job = jobService.findDetail(jobId);
    return CommonResponse.success(job);
  }


  @ApiOperation("删除工作")
  @PostMapping("delete/{jobId}")
  public CommonResponse delJob(@PathVariable(value = "jobId") String jobId) {
    jobService.removeJob(jobId);
    return CommonResponse.success();

  }


}
