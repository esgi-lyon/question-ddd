package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.ClientConfiguration;

@FeignClient(name="${createdByIdResource.name:createdByIdResource}", url="${createdByIdResource.url:http://localhost:8083}", configuration = ClientConfiguration.class)
public interface CreatedByIdResourceApiClient extends CreatedByIdResourceApi {
}
