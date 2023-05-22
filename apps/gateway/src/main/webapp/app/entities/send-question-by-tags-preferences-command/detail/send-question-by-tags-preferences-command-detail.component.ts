import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISendQuestionByTagsPreferencesCommand } from '../send-question-by-tags-preferences-command.model';

@Component({
  selector: 'jhi-send-question-by-tags-preferences-command-detail',
  templateUrl: './send-question-by-tags-preferences-command-detail.component.html',
})
export class SendQuestionByTagsPreferencesCommandDetailComponent implements OnInit {
  sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sendQuestionByTagsPreferencesCommand }) => {
      this.sendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
