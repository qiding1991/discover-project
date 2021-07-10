package com.kankan.discover.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value ="meetup",url = "https://secure.meetup.com/oauth2")
public interface MeetUpService {

  @GetMapping("authorize")
  String authorize(@RequestParam(value = "client_id") String clientId,
      @RequestParam(value = "response_type") String code,
      @RequestParam(value = "redirect_uri") String redirectUri);



  @GetMapping("access")
  String access(@RequestParam(value = "client_id") String clientId,
      @RequestParam(value = "client_secret") String clientSecret,
      @RequestParam(value = "grant_type") String grantType,
      @RequestParam(value = "redirect_uri") String redirectUri,
      @RequestParam(value = "code") String code);


}
