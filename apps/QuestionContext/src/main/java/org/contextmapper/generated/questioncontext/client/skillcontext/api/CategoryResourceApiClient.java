package org.contextmapper.generated.questioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.skillcontext.ClientConfiguration;

@FeignClient(name="${categoryResource.name:categoryResource}", url="${categoryResource.url:http://localhost:8083}", configuration = ClientConfiguration.class)
public interface CategoryResourceApiClient extends CategoryResourceApi {
}
