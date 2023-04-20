SHELL:=/bin/bash

install:
	npm install -g puml-for-markdown

ordered_list=gamedev_ContextMap BC_UserManagementContext_User BC_QuestionContext BC_SkillContext BC_SendQuestionContext BC_AnswerContext \
	BC_EvaluationContext BC_StatContext

diagrams=$(foreach file,$(ordered_list),$(wildcard src-gen/*$(file)*.puml))

markdown:
	@echo $(diagrams)
	echo '# Diagrams' > doc/diagrams.md
	for i in $(diagrams);do echo -e "![$$i](../dist_puml/$${i//puml/png})\n" | tee -a doc/diagrams.md; done
	puml-for-markdown -m doc/ -d -t
	cp -f src-gen/gamedev_ContextMap.png doc/
