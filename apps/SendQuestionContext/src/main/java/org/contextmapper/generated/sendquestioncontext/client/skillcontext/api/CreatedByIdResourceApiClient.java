package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.SkillContextClientConfiguration;

@FeignClient(name="${createdByIdResource.name:createdByIdResource}", url="${createdByIdResource.url:http://localhost:8083}", configuration = SkillContextClientConfiguration.class)
public interface CreatedByIdResourceApiClient extends CreatedByIdResourceApi {
}
