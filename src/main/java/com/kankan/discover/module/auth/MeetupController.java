package com.kankan.discover.module.auth;

import com.kankan.discover.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MeetupController {

  @GetMapping("getCode/authorize")
  public CommonResponse authorize(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) {
    log.info("responseCode ,code={},state={}");
    return CommonResponse.success();
  }
}
