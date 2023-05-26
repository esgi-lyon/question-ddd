package org.contextmapper.generated.evaluationcontext.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.AnswerCheckedEvent.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.EvaluationCreatedEvent.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.AwardedPointEvent.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.CheckAnswerCommand.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluationCommand.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.PointAwardRule.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.Evaluation.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.AnsweringUser.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.EvaluationTag.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.EvaluationQuestion.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.AnswerChecked.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.EvaluationCreated.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.AwardedPoint.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.CheckAnswer.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.CreateEvaluation.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluation.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.NewAnswerNotifiedEvent.class.getName());
            createCache(cm, org.contextmapper.generated.evaluationcontext.domain.NotifyNewAnswerCommand.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
