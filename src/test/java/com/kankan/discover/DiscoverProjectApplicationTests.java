package com.kankan.discover;

import com.kankan.discover.service.MeetUpService;
import java.net.URLEncoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootTest
class DiscoverProjectApplicationTests {

  @Autowired
  private MeetUpService meetUpService;

  @Test
  void contextLoads() {

  }

  @Test
  public void getCode() {
    String result = meetUpService.authorize("i404ih00qvk1n7sk9or61nv4q0", "code", URLEncoder.encode("http://47.94.33.237:8085/meetup/getCode/authorize"));
    log.info("result={}", result);
  }

}
