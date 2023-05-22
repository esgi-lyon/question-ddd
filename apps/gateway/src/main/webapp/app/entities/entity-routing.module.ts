import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'user-infos',
        data: { pageTitle: 'gatewayApp.userManagementContextUserInfos.home.title' },
        loadChildren: () =>
          import('./UserManagementContext/user-infos/user-infos.module').then(m => m.UserManagementContextUserInfosModule),
      },
      {
        path: 'validate-resource-tag-linkage-command',
        data: { pageTitle: 'gatewayApp.validateResourceTagLinkageCommand.home.title' },
        loadChildren: () =>
          import('./validate-resource-tag-linkage-command/validate-resource-tag-linkage-command.module').then(
            m => m.ValidateResourceTagLinkageCommandModule
          ),
      },
      {
        path: 'category-created-event',
        data: { pageTitle: 'gatewayApp.categoryCreatedEvent.home.title' },
        loadChildren: () => import('./category-created-event/category-created-event.module').then(m => m.CategoryCreatedEventModule),
      },
      {
        path: 'question-sent',
        data: { pageTitle: 'gatewayApp.sendQuestionContextQuestionSent.home.title' },
        loadChildren: () =>
          import('./SendQuestionContext/question-sent/question-sent.module').then(m => m.SendQuestionContextQuestionSentModule),
      },
      {
        path: 'answer-submit-command',
        data: { pageTitle: 'gatewayApp.answerSubmitCommand.home.title' },
        loadChildren: () => import('./answer-submit-command/answer-submit-command.module').then(m => m.AnswerSubmitCommandModule),
      },
      {
        path: 'answer-checked-event',
        data: { pageTitle: 'gatewayApp.answerCheckedEvent.home.title' },
        loadChildren: () => import('./answer-checked-event/answer-checked-event.module').then(m => m.AnswerCheckedEventModule),
      },
      {
        path: 'view-stats-command',
        data: { pageTitle: 'gatewayApp.viewStatsCommand.home.title' },
        loadChildren: () => import('./view-stats-command/view-stats-command.module').then(m => m.ViewStatsCommandModule),
      },
      {
        path: 'resource-waiting-for-association-event',
        data: { pageTitle: 'gatewayApp.resourceWaitingForAssociationEvent.home.title' },
        loadChildren: () =>
          import('./resource-waiting-for-association-event/resource-waiting-for-association-event.module').then(
            m => m.ResourceWaitingForAssociationEventModule
          ),
      },
      {
        path: 'tag-created-event',
        data: { pageTitle: 'gatewayApp.tagCreatedEvent.home.title' },
        loadChildren: () => import('./tag-created-event/tag-created-event.module').then(m => m.TagCreatedEventModule),
      },
      {
        path: 'notified-question-event',
        data: { pageTitle: 'gatewayApp.notifiedQuestionEvent.home.title' },
        loadChildren: () => import('./notified-question-event/notified-question-event.module').then(m => m.NotifiedQuestionEventModule),
      },
      {
        path: 'tag-choices-listed-event',
        data: { pageTitle: 'gatewayApp.tagChoicesListedEvent.home.title' },
        loadChildren: () => import('./tag-choices-listed-event/tag-choices-listed-event.module').then(m => m.TagChoicesListedEventModule),
      },
      {
        path: 'evaluation-created-event',
        data: { pageTitle: 'gatewayApp.evaluationCreatedEvent.home.title' },
        loadChildren: () => import('./evaluation-created-event/evaluation-created-event.module').then(m => m.EvaluationCreatedEventModule),
      },
      {
        path: 'question-stats-viewed-event',
        data: { pageTitle: 'gatewayApp.questionStatsViewedEvent.home.title' },
        loadChildren: () =>
          import('./question-stats-viewed-event/question-stats-viewed-event.module').then(m => m.QuestionStatsViewedEventModule),
      },
      {
        path: 'create-resource-command',
        data: { pageTitle: 'gatewayApp.createResourceCommand.home.title' },
        loadChildren: () => import('./create-resource-command/create-resource-command.module').then(m => m.CreateResourceCommandModule),
      },
      {
        path: 'create-tag-command',
        data: { pageTitle: 'gatewayApp.createTagCommand.home.title' },
        loadChildren: () => import('./create-tag-command/create-tag-command.module').then(m => m.CreateTagCommandModule),
      },
      {
        path: 'user-preferences',
        data: { pageTitle: 'gatewayApp.sendQuestionContextUserPreferences.home.title' },
        loadChildren: () =>
          import('./SendQuestionContext/user-preferences/user-preferences.module').then(m => m.SendQuestionContextUserPreferencesModule),
      },
      {
        path: 'tag-choices-list-command',
        data: { pageTitle: 'gatewayApp.tagChoicesListCommand.home.title' },
        loadChildren: () => import('./tag-choices-list-command/tag-choices-list-command.module').then(m => m.TagChoicesListCommandModule),
      },
      {
        path: 'awarded-point-event',
        data: { pageTitle: 'gatewayApp.awardedPointEvent.home.title' },
        loadChildren: () => import('./awarded-point-event/awarded-point-event.module').then(m => m.AwardedPointEventModule),
      },
      {
        path: 'user-stats-viewed-event',
        data: { pageTitle: 'gatewayApp.userStatsViewedEvent.home.title' },
        loadChildren: () => import('./user-stats-viewed-event/user-stats-viewed-event.module').then(m => m.UserStatsViewedEventModule),
      },
      {
        path: 'resource-accepted-association-event',
        data: { pageTitle: 'gatewayApp.resourceAcceptedAssociationEvent.home.title' },
        loadChildren: () =>
          import('./resource-accepted-association-event/resource-accepted-association-event.module').then(
            m => m.ResourceAcceptedAssociationEventModule
          ),
      },
      {
        path: 'create-category-command',
        data: { pageTitle: 'gatewayApp.createCategoryCommand.home.title' },
        loadChildren: () => import('./create-category-command/create-category-command.module').then(m => m.CreateCategoryCommandModule),
      },
      {
        path: 'created-question-event',
        data: { pageTitle: 'gatewayApp.createdQuestionEvent.home.title' },
        loadChildren: () => import('./created-question-event/created-question-event.module').then(m => m.CreatedQuestionEventModule),
      },
      {
        path: 'answer-submitted-event',
        data: { pageTitle: 'gatewayApp.answerSubmittedEvent.home.title' },
        loadChildren: () => import('./answer-submitted-event/answer-submitted-event.module').then(m => m.AnswerSubmittedEventModule),
      },
      {
        path: 'check-answer-command',
        data: { pageTitle: 'gatewayApp.checkAnswerCommand.home.title' },
        loadChildren: () => import('./check-answer-command/check-answer-command.module').then(m => m.CheckAnswerCommandModule),
      },
      {
        path: 'leader-board',
        data: { pageTitle: 'gatewayApp.statContextLeaderBoard.home.title' },
        loadChildren: () => import('./StatContext/leader-board/leader-board.module').then(m => m.StatContextLeaderBoardModule),
      },
      {
        path: 'question-resource',
        data: { pageTitle: 'gatewayApp.questionContextQuestionResource.home.title' },
        loadChildren: () =>
          import('./QuestionContext/question-resource/question-resource.module').then(m => m.QuestionContextQuestionResourceModule),
      },
      {
        path: 'category',
        data: { pageTitle: 'gatewayApp.skillContextCategory.home.title' },
        loadChildren: () => import('./SkillContext/category/category.module').then(m => m.SkillContextCategoryModule),
      },
      {
        path: 'send-question-by-tags-preferences-command',
        data: { pageTitle: 'gatewayApp.sendQuestionByTagsPreferencesCommand.home.title' },
        loadChildren: () =>
          import('./send-question-by-tags-preferences-command/send-question-by-tags-preferences-command.module').then(
            m => m.SendQuestionByTagsPreferencesCommandModule
          ),
      },
      {
        path: 'answer',
        data: { pageTitle: 'gatewayApp.answerContextAnswer.home.title' },
        loadChildren: () => import('./AnswerContext/answer/answer.module').then(m => m.AnswerContextAnswerModule),
      },
      {
        path: 'award-point-for-evaluation-command',
        data: { pageTitle: 'gatewayApp.awardPointForEvaluationCommand.home.title' },
        loadChildren: () =>
          import('./award-point-for-evaluation-command/award-point-for-evaluation-command.module').then(
            m => m.AwardPointForEvaluationCommandModule
          ),
      },
      {
        path: 'tag-stats-viewed-event',
        data: { pageTitle: 'gatewayApp.tagStatsViewedEvent.home.title' },
        loadChildren: () => import('./tag-stats-viewed-event/tag-stats-viewed-event.module').then(m => m.TagStatsViewedEventModule),
      },
      {
        path: 'reject-resource-tag-command',
        data: { pageTitle: 'gatewayApp.rejectResourceTagCommand.home.title' },
        loadChildren: () =>
          import('./reject-resource-tag-command/reject-resource-tag-command.module').then(m => m.RejectResourceTagCommandModule),
      },
      {
        path: 'tag',
        data: { pageTitle: 'gatewayApp.skillContextTag.home.title' },
        loadChildren: () => import('./SkillContext/tag/tag.module').then(m => m.SkillContextTagModule),
      },
      {
        path: 'create-question-command',
        data: { pageTitle: 'gatewayApp.createQuestionCommand.home.title' },
        loadChildren: () => import('./create-question-command/create-question-command.module').then(m => m.CreateQuestionCommandModule),
      },
      {
        path: 'point-award-rule',
        data: { pageTitle: 'gatewayApp.evaluationContextPointAwardRule.home.title' },
        loadChildren: () =>
          import('./EvaluationContext/point-award-rule/point-award-rule.module').then(m => m.EvaluationContextPointAwardRuleModule),
      },
      {
        path: 'resource-refused-association-event',
        data: { pageTitle: 'gatewayApp.resourceRefusedAssociationEvent.home.title' },
        loadChildren: () =>
          import('./resource-refused-association-event/resource-refused-association-event.module').then(
            m => m.ResourceRefusedAssociationEventModule
          ),
      },
      {
        path: 'create-evaluation-command',
        data: { pageTitle: 'gatewayApp.createEvaluationCommand.home.title' },
        loadChildren: () =>
          import('./create-evaluation-command/create-evaluation-command.module').then(m => m.CreateEvaluationCommandModule),
      },
      {
        path: 'evaluation',
        data: { pageTitle: 'gatewayApp.evaluationContextEvaluation.home.title' },
        loadChildren: () => import('./EvaluationContext/evaluation/evaluation.module').then(m => m.EvaluationContextEvaluationModule),
      },
      {
        path: 'question-resource-tag-infos',
        data: { pageTitle: 'gatewayApp.questionResourceTagInfos.home.title' },
        loadChildren: () =>
          import('./question-resource-tag-infos/question-resource-tag-infos.module').then(m => m.QuestionResourceTagInfosModule),
      },
      {
        path: 'tag-infos',
        data: { pageTitle: 'gatewayApp.tagInfos.home.title' },
        loadChildren: () => import('./tag-infos/tag-infos.module').then(m => m.TagInfosModule),
      },
      {
        path: 'category-id',
        data: { pageTitle: 'gatewayApp.categoryId.home.title' },
        loadChildren: () => import('./category-id/category-id.module').then(m => m.CategoryIdModule),
      },
      {
        path: 'user-with-preferences-id',
        data: { pageTitle: 'gatewayApp.userWithPreferencesId.home.title' },
        loadChildren: () => import('./user-with-preferences-id/user-with-preferences-id.module').then(m => m.UserWithPreferencesIdModule),
      },
      {
        path: 'user-preferences-tag-infos',
        data: { pageTitle: 'gatewayApp.userPreferencesTagInfos.home.title' },
        loadChildren: () =>
          import('./user-preferences-tag-infos/user-preferences-tag-infos.module').then(m => m.UserPreferencesTagInfosModule),
      },
      {
        path: 'question-sent-tag-id',
        data: { pageTitle: 'gatewayApp.questionSentTagId.home.title' },
        loadChildren: () => import('./question-sent-tag-id/question-sent-tag-id.module').then(m => m.QuestionSentTagIdModule),
      },
      {
        path: 'question-sent-tag-infos',
        data: { pageTitle: 'gatewayApp.questionSentTagInfos.home.title' },
        loadChildren: () => import('./question-sent-tag-infos/question-sent-tag-infos.module').then(m => m.QuestionSentTagInfosModule),
      },
      {
        path: 'question',
        data: { pageTitle: 'gatewayApp.question.home.title' },
        loadChildren: () => import('./question/question.module').then(m => m.QuestionModule),
      },
      {
        path: 'question-id',
        data: { pageTitle: 'gatewayApp.questionId.home.title' },
        loadChildren: () => import('./question-id/question-id.module').then(m => m.QuestionIdModule),
      },
      {
        path: 'answered-tag',
        data: { pageTitle: 'gatewayApp.answeredTag.home.title' },
        loadChildren: () => import('./answered-tag/answered-tag.module').then(m => m.AnsweredTagModule),
      },
      {
        path: 'answering-user',
        data: { pageTitle: 'gatewayApp.answeringUser.home.title' },
        loadChildren: () => import('./answering-user/answering-user.module').then(m => m.AnsweringUserModule),
      },
      {
        path: 'evaluation-tag',
        data: { pageTitle: 'gatewayApp.evaluationTag.home.title' },
        loadChildren: () => import('./evaluation-tag/evaluation-tag.module').then(m => m.EvaluationTagModule),
      },
      {
        path: 'evaluation-question',
        data: { pageTitle: 'gatewayApp.evaluationQuestion.home.title' },
        loadChildren: () => import('./evaluation-question/evaluation-question.module').then(m => m.EvaluationQuestionModule),
      },
      {
        path: 'evaluation-id',
        data: { pageTitle: 'gatewayApp.evaluationId.home.title' },
        loadChildren: () => import('./evaluation-id/evaluation-id.module').then(m => m.EvaluationIdModule),
      },
      {
        path: 'question-sent-question-resource-tag-id',
        data: { pageTitle: 'gatewayApp.questionSentQuestionResourceTagId.home.title' },
        loadChildren: () =>
          import('./question-sent-question-resource-tag-id/question-sent-question-resource-tag-id.module').then(
            m => m.QuestionSentQuestionResourceTagIdModule
          ),
      },
      {
        path: 'evaluated-answer',
        data: { pageTitle: 'gatewayApp.evaluatedAnswer.home.title' },
        loadChildren: () => import('./evaluated-answer/evaluated-answer.module').then(m => m.EvaluatedAnswerModule),
      },
      {
        path: 'statistic-subject-tag',
        data: { pageTitle: 'gatewayApp.statisticSubjectTag.home.title' },
        loadChildren: () => import('./statistic-subject-tag/statistic-subject-tag.module').then(m => m.StatisticSubjectTagModule),
      },
      {
        path: 'statistic-subject-user',
        data: { pageTitle: 'gatewayApp.statisticSubjectUser.home.title' },
        loadChildren: () => import('./statistic-subject-user/statistic-subject-user.module').then(m => m.StatisticSubjectUserModule),
      },
      {
        path: 'statistic-subject-question',
        data: { pageTitle: 'gatewayApp.statisticSubjectQuestion.home.title' },
        loadChildren: () =>
          import('./statistic-subject-question/statistic-subject-question.module').then(m => m.StatisticSubjectQuestionModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
