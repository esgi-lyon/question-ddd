package org.contextmapper.generated.questioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.skillcontext.SkillContextClientConfiguration;

@FeignClient(name="${tagResource.name:tagResource}", url="${tagResource.url:http://localhost:8093}", configuration = SkillContextClientConfiguration.class)
public interface TagResourceApiClient extends TagResourceApi {
}
