package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.SkillContextClientConfiguration;

@FeignClient(name="${categoryCreatedEventResource.name:categoryCreatedEventResource}", url="${categoryCreatedEventResource.url:http://localhost:8083}", configuration = SkillContextClientConfiguration.class)
public interface CategoryCreatedEventResourceApiClient extends CategoryCreatedEventResourceApi {
}
