package org.contextmapper.generated.questioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.skillcontext.SkillContextClientConfiguration;

@FeignClient(name="${categoryCreatedEventResource.name:categoryCreatedEventResource}", url="${categoryCreatedEventResource.url:http://localhost:8093}", configuration = SkillContextClientConfiguration.class)
public interface CategoryCreatedEventResourceApiClient extends CategoryCreatedEventResourceApi {
}
