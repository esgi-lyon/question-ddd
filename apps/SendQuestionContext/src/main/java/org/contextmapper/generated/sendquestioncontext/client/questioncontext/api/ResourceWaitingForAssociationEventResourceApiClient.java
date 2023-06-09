package org.contextmapper.generated.sendquestioncontext.client.questioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.QuestionContextClientConfiguration;

@FeignClient(name="${resourceWaitingForAssociationEventResource.name:resourceWaitingForAssociationEventResource}", url="${resourceWaitingForAssociationEventResource.url:http://localhost:8092}", configuration = QuestionContextClientConfiguration.class)
public interface ResourceWaitingForAssociationEventResourceApiClient extends ResourceWaitingForAssociationEventResourceApi {
}
