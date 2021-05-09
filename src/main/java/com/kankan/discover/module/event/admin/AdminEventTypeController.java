package com.kankan.discover.module.event.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.model.event.EventType;
import com.kankan.discover.module.event.param.CreateEventTypeParam;
import com.kankan.discover.service.EventTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "管理后台-活动类型管理")
@RestController
@RequestMapping("admin/eventType")
public class AdminEventTypeController {

    @Autowired
    private EventTypeService eventTypeService;

    @ApiOperation("创建或者更新eventType(eventId 为空创建；不为空更新)")
    @PostMapping("createOrUpdate")
    public CommonResponse createOrUpdate(@RequestBody CreateEventTypeParam typeParam) {
        EventType eventType = new EventType(typeParam);
        EventType result = eventTypeService.save(eventType);
        return CommonResponse.success(result);
    }

    @ApiOperation("分类列表(分类比较少，不分页)")
    @GetMapping("list")
    public CommonResponse list() {
         List<EventType> eventTypeList = eventTypeService.eventList();
        return CommonResponse.success(eventTypeList);
    }

    @ApiOperation("删除分类")
    @PostMapping("del/{eventId}")
    public CommonResponse delEvent(@PathVariable(value = "eventId") String eventId){
        eventTypeService.remove(eventId);
        return CommonResponse.success();
    }
}
