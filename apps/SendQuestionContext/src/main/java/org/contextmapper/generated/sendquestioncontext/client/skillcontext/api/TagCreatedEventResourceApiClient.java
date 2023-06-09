package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.SkillContextClientConfiguration;

@FeignClient(name="${tagCreatedEventResource.name:tagCreatedEventResource}", url="${tagCreatedEventResource.url:http://localhost:8093}", configuration = SkillContextClientConfiguration.class)
public interface TagCreatedEventResourceApiClient extends TagCreatedEventResourceApi {
}
