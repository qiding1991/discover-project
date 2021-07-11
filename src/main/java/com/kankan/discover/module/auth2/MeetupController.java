package com.kankan.discover.module.auth2;

import com.kankan.discover.common.CommonResponse;
import com.kankan.discover.config.MeetupConfig;
import com.kankan.discover.service.MeetUpService;
import com.kankan.discover.service.MeetUpService.AccessToken;
import java.net.URLEncoder;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("meetup")
public class MeetupController {

  @Autowired
  private MeetupConfig meetupConfig;

  @Autowired
  private MeetUpService meetUpService;

  private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);


  @GetMapping("getCode/authorize")
  public CommonResponse authorize(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state)
      throws InterruptedException {
    log.info("responseCode ,code={},state={}", code, state);
    AccessToken access_token = meetUpService
        .access(meetupConfig.getClientId(), meetupConfig.getClientSecret(), "authorization_code", meetupConfig.getRedirectUri(), code);
    log.info("access_token ={}", access_token);
    blockingQueue.put(access_token.getAccess_token());
    return CommonResponse.success();
  }

  @GetMapping("getAccessToken")
  public CommonResponse getAccessToken() throws InterruptedException {
    String result = meetUpService.authorize(meetupConfig.getClientId(), meetupConfig.getClientSecret(), URLEncoder.encode(meetupConfig.getRedirectUri()));
    log.info("getAccessToken result={}", result);
    String accessToken = blockingQueue.take();
    return CommonResponse.success(accessToken);
  }


}
