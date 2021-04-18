package com.kankan.discover.module.event.admin;

import com.google.common.collect.ImmutableMap;
import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.model.event.Event;
import com.kankan.discover.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "管理后台-活动管理")
@RestController
@RequestMapping("admin/event")
public class AdminEventController {

  @Autowired
  private EventService eventService;

  @ApiOperation("活动列表")
  @GetMapping("list")
  public CommonResponse listEvent(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

    List<Event> eventList = eventService.findEvent((pageNumber - 1) * pageSize, pageSize);
    Long totalCount = eventService.count();
    Integer totalPage = totalCount / pageSize + totalCount % pageSize == 0 ? 0 : 1;
    Map<String, Object> resultMap = ImmutableMap.of("infoList", eventList, "totalCount", totalCount, "totalPage", totalPage);
    return CommonResponse.success(resultMap);

  }

  @ApiOperation("活动详情")
  @GetMapping("detail/{eventId}")
  public CommonResponse detailEvent(@PathVariable(value = "eventId") String eventId) {
    Event event = eventService.findEvent(eventId);
    return CommonResponse.success(event);
  }


  @ApiOperation("删除活动")
  @PostMapping("delete/{eventId}")
  public CommonResponse delEvent(@PathVariable(value = "eventId") String eventId) {
    eventService.remove(eventId);
    return CommonResponse.success();
  }




}
