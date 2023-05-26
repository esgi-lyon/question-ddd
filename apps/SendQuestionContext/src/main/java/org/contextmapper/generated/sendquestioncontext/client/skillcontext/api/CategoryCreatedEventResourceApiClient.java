package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.SkillClientConfiguration;

@FeignClient(name="${categoryCreatedEventResource.name:categoryCreatedEventResource}", url="${categoryCreatedEventResource.url:http://localhost:8083}", configuration = SkillClientConfiguration.class)
public interface CategoryCreatedEventResourceApiClient extends CategoryCreatedEventResourceApi {
}
