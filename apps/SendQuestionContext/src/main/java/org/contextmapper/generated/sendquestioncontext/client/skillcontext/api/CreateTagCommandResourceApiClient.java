package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.SkillClientConfiguration;

@FeignClient(name="${createTagCommandResource.name:createTagCommandResource}", url="${createTagCommandResource.url:http://localhost:8083}", configuration = SkillClientConfiguration.class)
public interface CreateTagCommandResourceApiClient extends CreateTagCommandResourceApi {
}
