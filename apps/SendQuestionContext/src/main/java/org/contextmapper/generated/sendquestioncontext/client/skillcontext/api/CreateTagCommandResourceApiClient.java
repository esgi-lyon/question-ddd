package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.ClientConfiguration;

@FeignClient(name="${createTagCommandResource.name:createTagCommandResource}", url="${createTagCommandResource.url:http://localhost:8083}", configuration = ClientConfiguration.class)
public interface CreateTagCommandResourceApiClient extends CreateTagCommandResourceApi {
}
