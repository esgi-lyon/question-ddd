SHELL:=/bin/bash

install:
	pnpm install -g puml-for-markdown
	pnpm install lz-string
	pnpm install puppeteer
	pnpm install typescript

ordered_list=gamedev_ContextMap BC_UserManagementContext_User BC_QuestionContext BC_SkillContext BC_SendQuestionContext BC_AnswerContext BC_EvaluationContext BC_StatContext

diagrams=$(foreach file,$(ordered_list),$(wildcard src-gen/*$(file)*.puml) $(wildcard src-gen/*$(file)*.sketch_miner))
sketch_only=$(foreach file,$(ordered_list),$(wildcard src-gen/*$(file)*.sketch_miner))

print:
	@echo $(diagrams)
	@echo "------------------------------"
	@echo $(sketch_only)

markdown:
	echo '# Diagrams' > doc/diagrams.md
	for i in $(diagrams);do i=$${i//sketch_miner/sketch_miner.png} && echo -e "![$$i](../dist_puml/$${i//puml/png})\n" | tee -a doc/diagrams.md; done
	puml-for-markdown -m doc/ -d -t
	./bnpm-sketch-gen.js dist_puml/src-gen/ $(strip $(sketch_only))
	cp -f src-gen/gamedev_ContextMap.png doc/

jhipster-gen:
	@echo 'TODO'
