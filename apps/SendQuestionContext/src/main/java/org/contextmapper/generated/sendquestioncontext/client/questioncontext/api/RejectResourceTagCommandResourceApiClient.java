package org.contextmapper.generated.sendquestioncontext.client.questioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.ClientConfiguration;

@FeignClient(name="${rejectResourceTagCommandResource.name:rejectResourceTagCommandResource}", url="${rejectResourceTagCommandResource.url:http://localhost:8082}", configuration = ClientConfiguration.class)
public interface RejectResourceTagCommandResourceApiClient extends RejectResourceTagCommandResourceApi {
}
