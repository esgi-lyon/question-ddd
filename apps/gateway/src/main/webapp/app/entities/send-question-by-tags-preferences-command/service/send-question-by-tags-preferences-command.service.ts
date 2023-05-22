import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import {
  ISendQuestionByTagsPreferencesCommand,
  NewSendQuestionByTagsPreferencesCommand,
} from '../send-question-by-tags-preferences-command.model';

export type PartialUpdateSendQuestionByTagsPreferencesCommand = Partial<ISendQuestionByTagsPreferencesCommand> &
  Pick<ISendQuestionByTagsPreferencesCommand, 'id'>;

export type EntityResponseType = HttpResponse<ISendQuestionByTagsPreferencesCommand>;
export type EntityArrayResponseType = HttpResponse<ISendQuestionByTagsPreferencesCommand[]>;

@Injectable({ providedIn: 'root' })
export class SendQuestionByTagsPreferencesCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/send-question-by-tags-preferences-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sendQuestionByTagsPreferencesCommand: NewSendQuestionByTagsPreferencesCommand): Observable<EntityResponseType> {
    return this.http.post<ISendQuestionByTagsPreferencesCommand>(this.resourceUrl, sendQuestionByTagsPreferencesCommand, {
      observe: 'response',
    });
  }

  update(sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand): Observable<EntityResponseType> {
    return this.http.put<ISendQuestionByTagsPreferencesCommand>(
      `${this.resourceUrl}/${this.getSendQuestionByTagsPreferencesCommandIdentifier(sendQuestionByTagsPreferencesCommand)}`,
      sendQuestionByTagsPreferencesCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(sendQuestionByTagsPreferencesCommand: PartialUpdateSendQuestionByTagsPreferencesCommand): Observable<EntityResponseType> {
    return this.http.patch<ISendQuestionByTagsPreferencesCommand>(
      `${this.resourceUrl}/${this.getSendQuestionByTagsPreferencesCommandIdentifier(sendQuestionByTagsPreferencesCommand)}`,
      sendQuestionByTagsPreferencesCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISendQuestionByTagsPreferencesCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISendQuestionByTagsPreferencesCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSendQuestionByTagsPreferencesCommandIdentifier(
    sendQuestionByTagsPreferencesCommand: Pick<ISendQuestionByTagsPreferencesCommand, 'id'>
  ): number {
    return sendQuestionByTagsPreferencesCommand.id;
  }

  compareSendQuestionByTagsPreferencesCommand(
    o1: Pick<ISendQuestionByTagsPreferencesCommand, 'id'> | null,
    o2: Pick<ISendQuestionByTagsPreferencesCommand, 'id'> | null
  ): boolean {
    return o1 && o2
      ? this.getSendQuestionByTagsPreferencesCommandIdentifier(o1) === this.getSendQuestionByTagsPreferencesCommandIdentifier(o2)
      : o1 === o2;
  }

  addSendQuestionByTagsPreferencesCommandToCollectionIfMissing<Type extends Pick<ISendQuestionByTagsPreferencesCommand, 'id'>>(
    sendQuestionByTagsPreferencesCommandCollection: Type[],
    ...sendQuestionByTagsPreferencesCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sendQuestionByTagsPreferencesCommands: Type[] = sendQuestionByTagsPreferencesCommandsToCheck.filter(isPresent);
    if (sendQuestionByTagsPreferencesCommands.length > 0) {
      const sendQuestionByTagsPreferencesCommandCollectionIdentifiers = sendQuestionByTagsPreferencesCommandCollection.map(
        sendQuestionByTagsPreferencesCommandItem =>
          this.getSendQuestionByTagsPreferencesCommandIdentifier(sendQuestionByTagsPreferencesCommandItem)!
      );
      const sendQuestionByTagsPreferencesCommandsToAdd = sendQuestionByTagsPreferencesCommands.filter(
        sendQuestionByTagsPreferencesCommandItem => {
          const sendQuestionByTagsPreferencesCommandIdentifier = this.getSendQuestionByTagsPreferencesCommandIdentifier(
            sendQuestionByTagsPreferencesCommandItem
          );
          if (sendQuestionByTagsPreferencesCommandCollectionIdentifiers.includes(sendQuestionByTagsPreferencesCommandIdentifier)) {
            return false;
          }
          sendQuestionByTagsPreferencesCommandCollectionIdentifiers.push(sendQuestionByTagsPreferencesCommandIdentifier);
          return true;
        }
      );
      return [...sendQuestionByTagsPreferencesCommandsToAdd, ...sendQuestionByTagsPreferencesCommandCollection];
    }
    return sendQuestionByTagsPreferencesCommandCollection;
  }
}
