package com.kankan.discover.module.job.user;

import java.util.List;

import com.kankan.discover.module.job.param.ApplyOrFavouriteJobParam;
import com.kankan.discover.module.job.param.PublishJobParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.model.job.Job;
import com.kankan.discover.service.JobService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "工作接口")
@RestController
@RequestMapping("job/user")
public class JobController {

  @Autowired
  private JobService jobService;

  @ApiOperation("工作列表")
  @GetMapping("list")
  public CommonResponse list(
    @RequestParam(value = "x", required = false) Double longitude,
    @RequestParam(value = "y", required = false) Double latitude,
    @RequestParam(value = "distance", required = false, defaultValue = "100") Double maxDistance,
    @RequestParam(value = "area", required = false) String area,
    @RequestParam(value = "timeOrder", required = false, defaultValue = "1") Integer timeOrder,
    @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
    @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
    List<Job> jobList = jobService.find(longitude, latitude, maxDistance, area, timeOrder, startIndex, limit);
    return CommonResponse.success(jobList);
  }

  @ApiOperation("发布工作")
  @PostMapping("publish")
  public CommonResponse publish(@RequestBody PublishJobParam publishJobParam) {
    Job job = new Job(publishJobParam);
    Job result = jobService.publishJob(job);
    return CommonResponse.success(result);
  }

  @ApiOperation("工作详情")
  @GetMapping("detail/{jobId}")
  public CommonResponse jobDetail(@PathVariable(value = "jobId") String jobId) {
    Job job = jobService.findDetail(jobId);
    return CommonResponse.success(job);
  }

  @ApiOperation("申请工作")
  @PostMapping("apply")
  public CommonResponse apply(@RequestBody ApplyOrFavouriteJobParam applyOrFavouriteJobParam) {
    Boolean applyResult = jobService.applyJob(applyOrFavouriteJobParam);
    return CommonResponse.success(applyResult);
  }

  @ApiOperation("收藏工作")
  @PostMapping("favourite")
  public CommonResponse favourite(@RequestBody ApplyOrFavouriteJobParam applyOrFavouriteJobParam) {
    Boolean applyResult = jobService.favourite(applyOrFavouriteJobParam);
    return CommonResponse.success(applyResult);
  }

  @ApiOperation("新发布工作")
  @GetMapping("recentJob")
  public CommonResponse recentJob(@RequestParam(value = "startIndex") Integer startIndex,
                                  @RequestParam(value = "limit") Integer limit) {
    List<Job> recentJob = jobService.findRecent(startIndex, limit);
    return CommonResponse.success(recentJob);
  }
}
