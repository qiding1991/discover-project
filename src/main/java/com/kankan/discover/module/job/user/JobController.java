package com.kankan.discover.module.job.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(value = "longitude") Double longitude,
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "area") String area,
            @RequestParam(value = "timeOrder") Integer timeOrder,
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
            @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
        List<Job> jobList = jobService.find(longitude, latitude, area, timeOrder, startIndex, limit);
        return CommonResponse.success(jobList);

    }

    @ApiOperation("发布工作")
    @PostMapping("publish")
    public CommonResponse publish() {
        Job job=new Job();
        Job result = jobService.publishJob(job);
        return CommonResponse.success(result);

    }


    @ApiOperation("工作详情")
    @GetMapping("detail/{jobId}")
    public CommonResponse jobDetail(@PathVariable(value = "jobId") String jobId) {
        Job job = jobService.findDetail(jobId);
        return CommonResponse.success(job);
    }

    @ApiOperation("新发布工作")
    @GetMapping("recentJob")
    public CommonResponse recentJob(){

    }

}
