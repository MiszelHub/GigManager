package com.gigmanager.gigservice.client;

import com.gigmanager.gigservice.domain.Band;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AuthorizedFeignClient(name = "bandservice")
public interface BandClient {

    @GetMapping("api/bands/{id}")
    Band findBandById(@PathVariable String id);
    @GetMapping("api/bands/{userId}")
    Band findBandByUserId(@PathVariable String userId);
}
