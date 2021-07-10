package com.kankan.discover;

import com.kankan.discover.service.MeetUpService;
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
    ResponseEntity<String> result = meetUpService.authorize("i404ih00qvk1n7sk9or61nv4q0", "code","code");
    log.info("result={}", result);
  }

}
