package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.SkillClientConfiguration;

@FeignClient(name="${tagCreatedEventResource.name:tagCreatedEventResource}", url="${tagCreatedEventResource.url:http://localhost:8083}", configuration = SkillClientConfiguration.class)
public interface TagCreatedEventResourceApiClient extends TagCreatedEventResourceApi {
}
