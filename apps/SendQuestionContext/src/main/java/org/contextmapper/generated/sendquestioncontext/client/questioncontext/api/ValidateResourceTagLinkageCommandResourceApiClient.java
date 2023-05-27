package org.contextmapper.generated.sendquestioncontext.client.questioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.ClientConfiguration;

@FeignClient(name="${validateResourceTagLinkageCommandResource.name:validateResourceTagLinkageCommandResource}", url="${validateResourceTagLinkageCommandResource.url:http://localhost:8082}", configuration = ClientConfiguration.class)
public interface ValidateResourceTagLinkageCommandResourceApiClient extends ValidateResourceTagLinkageCommandResourceApi {
}
