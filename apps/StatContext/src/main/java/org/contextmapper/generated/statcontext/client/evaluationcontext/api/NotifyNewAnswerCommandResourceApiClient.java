package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.statcontext.client.evaluationcontext.ClientConfiguration;

@FeignClient(name="${notifyNewAnswerCommandResource.name:notifyNewAnswerCommandResource}", url="${notifyNewAnswerCommandResource.url:http://localhost:8087}", configuration = ClientConfiguration.class)
public interface NotifyNewAnswerCommandResourceApiClient extends NotifyNewAnswerCommandResourceApi {
}
