SHELL:=/bin/bash

ordered_list=gamedev_ContextMap BC_UserManagementContext_User BC_QuestionContext BC_SkillContext BC_SendQuestionContext BC_AnswerContext BC_EvaluationContext BC_StatContext
diagrams=$(foreach file,$(ordered_list),$(wildcard src-gen/*$(file)*.puml) $(wildcard src-gen/*$(file)*.sketch_miner))
sketch_only=$(foreach file,$(ordered_list),$(wildcard src-gen/*$(file)*.sketch_miner))

JHIPSTER_VERSION=7.4.0
microservices:=UserManagementContext QuestionContext SkillContext SendQuestionContext AnswerContext EvaluationContext StatContext
targets:=$(microservices)

.PHONY: all $(targets) help build jhipster-registry run dev markdown

.DEFAULT_GOAL := help

install:
	npm install
	npm install -g generator-jhipster
	npm install -g rimraf
	wget https://github.com/jhipster/jhipster-registry/releases/download/v$(JHIPSTER_VERSION)/jhipster-registry-$(JHIPSTER_VERSION).jar

help:
	@echo $(diagrams)
	@echo "------------------------------"
	@echo $(sketch_only)
	@echo "------------------------------"
	@echo "available microservices to generate : $(microservices)"

diagrams:
	@echo '# Diagrams' > doc/diagrams.md
	for i in $(diagrams);do i=$${i//sketch_miner/sketch_miner.png} && echo -e "![$$i](../dist_puml/$${i//puml/png})\n" | tee -a doc/diagrams.md; done
	@npx puml-for-markdown -m doc/ -d -t
	./bnpm-sketch-gen.js dist_puml/src-gen/ $(strip $(sketch_only))
	@cp -f src-gen/gamedev_ContextMap.png doc/

build:	
	cd apps && \
	javac --version && \
	jhipster --force import-jdl ./../src-gen/output.jdl &&\ 
	cd -

docker-consul:
	docker-compose -f apps/UserManagementContext/src/main/docker/consul.yml up -d

$(targets):
	@cd apps/$@ && ./mvnw -P dev,api-docs,tls

# Use make -j8
all: $(targets)

start:
	make docker-consul
	$(MAKE) all -j8
