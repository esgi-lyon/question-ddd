SHELL:=/bin/bash

.PHONY: all $(targets) help install build jhipster-registry run dev markdown

.DEFAULT_GOAL := help

install:
	npm install -g puml-for-markdown
	npm install lz-string
	npm install puppeteer
	npm install typescript
	npm install -g generator-jhipster
	npm install -g rimraf
	wget https://github.com/jhipster/jhipster-registry/releases/download/$(JHIPSTER_VERSION)/jhipster-registry-$(JHIPSTER_VERSION).jar

ordered_list=gamedev_ContextMap BC_UserManagementContext_User BC_QuestionContext BC_SkillContext BC_SendQuestionContext BC_AnswerContext BC_EvaluationContext BC_StatContext
diagrams=$(foreach file,$(ordered_list),$(wildcard src-gen/*$(file)*.puml) $(wildcard src-gen/*$(file)*.sketch_miner))
sketch_only=$(foreach file,$(ordered_list),$(wildcard src-gen/*$(file)*.sketch_miner))

JHIPSTER_VERSION=7.4.0
microservices:=UserManagementContext QuestionContext SkillContext SendQuestionContext AnswerContext EvaluationContext StatContext
targets:=$(microservices)

help:
	@echo $(diagrams)
	@echo "------------------------------"
	@echo $(sketch_only)
	@echo "------------------------------"
	@echo "available microservices to generate : $(microservices)"

markdown:
	@echo '# Diagrams' > doc/diagrams.md
	for i in $(diagrams);do i=$${i//sketch_miner/sketch_miner.png} && echo -e "![$$i](../dist_puml/$${i//puml/png})\n" | tee -a doc/diagrams.md; done
	@puml-for-markdown -m doc/ -d -t
	./bnpm-sketch-gen.js dist_puml/src-gen/ $(strip $(sketch_only))
	@cp -f src-gen/gamedev_ContextMap.png doc/

all: jhipster-registry $(targets)

$(targets):
	@cd jhipster/$@ && ./mvnw && cd -

build:
	cd apps && \
	javac --version && \
	jhipster import-jdl ./../src-gen/output.jdl &&\ 
	cd -

jhipster-registry:
	java -jar jhipster-registry-$(JHIPSTER_VERSION).jar --spring.profiles.active=dev --spring.security.user.password=admin \
		--jhipster.security.authentication.jwt.secret=my-secret-key-which-should-be-changed-in-production-and-be-base64-encoded \
		--spring.cloud.config.server.composite.0.type=git \
		--spring.cloud.config.server.composite.0.uri=https://github.com/jhipster/jhipster-registry-sample-config
