package org.contextmapper.generated.questioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.usermanagementcontext.UserManagementClientConfiguration;

@FeignClient(name="${queryHandlerResource.name:queryHandlerResource}", url="${queryHandlerResource.url:http://localhost:8091}", configuration = UserManagementClientConfiguration.class)
public interface QueryHandlerResourceApiClient extends QueryHandlerResourceApi {
}
