package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.SkillContextClientConfiguration;

@FeignClient(name="${tagResource.name:tagResource}", url="${tagResource.url:http://localhost:8083}", configuration = SkillContextClientConfiguration.class)
public interface TagResourceApiClient extends TagResourceApi {
}
