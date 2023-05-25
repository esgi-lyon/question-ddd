package org.contextmapper.generated.sendquestioncontext.client.questioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.QuestionContextClientConfiguration;

@FeignClient(name="${resourceAcceptedAssociationEventResource.name:resourceAcceptedAssociationEventResource}", url="${resourceAcceptedAssociationEventResource.url:http://localhost:8082}", configuration = QuestionContextClientConfiguration.class)
public interface ResourceAcceptedAssociationEventResourceApiClient extends ResourceAcceptedAssociationEventResourceApi {
}
