package org.contextmapper.generated.questioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.skillcontext.SkillContextClientConfiguration;

@FeignClient(name="${categoryResource.name:categoryResource}", url="${categoryResource.url:http://localhost:8093}", configuration = SkillContextClientConfiguration.class)
public interface CategoryResourceApiClient extends CategoryResourceApi {
}
