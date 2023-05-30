package org.contextmapper.generated.sendquestioncontext.client.questioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.QuestionContextClientConfiguration;

@FeignClient(name="${resourceRefusedAssociationEventResource.name:resourceRefusedAssociationEventResource}", url="${resourceRefusedAssociationEventResource.url:http://localhost:8092}", configuration = QuestionContextClientConfiguration.class)
public interface ResourceRefusedAssociationEventResourceApiClient extends ResourceRefusedAssociationEventResourceApi {
}
