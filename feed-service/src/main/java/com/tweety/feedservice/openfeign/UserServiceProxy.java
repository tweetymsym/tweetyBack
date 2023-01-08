package com.tweety.feedservice.openfeign;

import com.tweety.feedservice.configuration.FeignConfiguration;
import com.tweety.feedservice.dto.UserIdListDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", contextId = "user")
public interface UserServiceProxy {

    @GetMapping("/api/users/follow/following/id-list/{userId}")
    ResponseEntity<UserIdListDto> getFollowingIdList(
            @PathVariable("userId") String userId);
}
