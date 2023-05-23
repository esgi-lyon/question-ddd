package org.contextmapper.generated.questioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.skillcontext.ClientConfiguration;

@FeignClient(name="${tagResource.name:tagResource}", url="${tagResource.url:http://localhost:8083}", configuration = ClientConfiguration.class)
public interface TagResourceApiClient extends TagResourceApi {
}
