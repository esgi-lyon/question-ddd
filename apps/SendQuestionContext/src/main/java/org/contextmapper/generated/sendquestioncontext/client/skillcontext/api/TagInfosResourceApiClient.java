package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.SkillContextClientConfiguration;

@FeignClient(name="${tagInfosResource.name:tagInfosResource}", url="${tagInfosResource.url:http://localhost:8083}", configuration = SkillContextClientConfiguration.class)
public interface TagInfosResourceApiClient extends TagInfosResourceApi {
}
