package com.kankan.discover.module.event.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.model.event.Event;
import com.kankan.discover.module.event.param.JoinEventParam;
import com.kankan.discover.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@Api(tags = "活动入口")
@RestController
@RequestMapping("event/user")
public class EventController {

    @Autowired
    private EventService eventService;


    @ApiOperation("活动列表")
    @GetMapping("findByEventTypeId")
    public CommonResponse findByEventTypeId(
            @RequestParam(value = "eventTypeId", required = false) String eventTypeId,
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
            @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {

        List<Event> eventList = eventService.findEvent(eventTypeId, startIndex, limit);
        return CommonResponse.success(eventList);
    }

    @ApiOperation("活动详情")
    @GetMapping("detail/{eventId}")
    public CommonResponse detail(@PathVariable(value = "eventId") String eventId) {
        Event event = eventService.findEvent(eventId);
        return CommonResponse.success(event);
    }


    @ApiOperation("查找活动")
    @GetMapping("findEvent")
    public CommonResponse findEvent(@RequestParam(value = "longitude") Double longitude,
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "area") String area,
            @RequestParam(value = "timeOrder") Integer timeOrder,
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) Integer startIndex,
            @RequestParam(value = "limit", defaultValue = "30", required = false) Integer limit) {
        List<Event> eventList = eventService.findEvent(longitude, latitude, area, timeOrder, startIndex, limit);
        return CommonResponse.success(eventList);
    }

    @ApiOperation("参加活动")
    @PostMapping("joinEvent")
    public CommonResponse joinEvent(@RequestBody JoinEventParam param) {
        eventService.joinEvent(param.getEventId(),param.toUserEvent());
        return CommonResponse.success();
    }

    @ApiOperation("近期活动")
    @GetMapping("recentEvent")
    public CommonResponse recentEvent(){
      return CommonResponse.success();
    }


}
