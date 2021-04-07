package com.kankan.discover.module.event.admin;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "管理后台-活动管理")
@RestController
@RequestMapping("admin/event")
public class AdminEventController {

  @Autowired
  private EventService eventService;

  @ApiOperation("活动列表")
  @GetMapping("list")
  public CommonResponse listEvent(@RequestParam(value = "startIndex", required = false, defaultValue = "1") Integer pageNumer,
                                  @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {


  }

  @ApiOperation("活动详情")
  @GetMapping("detail/{eventId}")
  public CommonResponse detailEvent(@PathVariable(value = "jobId") String jobId) {

  }


  @ApiOperation("删除活动")
  @PostMapping("delete/{eventId}")
  public CommonResponse delEvent(@PathVariable(value = "jobId") String jobId) {

  }


}
