package com.kankan.discover.service;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value ="meetup",url = "https://secure.meetup.com/oauth2")
public interface MeetUpService {

  @Data
    class AccessToken {
    private String access_token;
    private String token_type;
    private Integer expires_in;
    private String refresh_token;
  }


  @GetMapping("authorize")
  String authorize(@RequestParam(value = "client_id") String clientId,
      @RequestParam(value = "response_type") String code,
      @RequestParam(value = "redirect_uri") String redirectUri);



  @GetMapping("access")
  AccessToken access(@RequestParam(value = "client_id") String clientId,
      @RequestParam(value = "client_secret") String clientSecret,
      @RequestParam(value = "grant_type") String grantType,
      @RequestParam(value = "redirect_uri") String redirectUri,
      @RequestParam(value = "code") String code);


}
