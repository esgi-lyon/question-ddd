package org.contextmapper.generated.questioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.skillcontext.SkillContextClientConfiguration;

@FeignClient(name="${tagCreatedEventResource.name:tagCreatedEventResource}", url="${tagCreatedEventResource.url:http://localhost:8093}", configuration = SkillContextClientConfiguration.class)
public interface TagCreatedEventResourceApiClient extends TagCreatedEventResourceApi {
}
