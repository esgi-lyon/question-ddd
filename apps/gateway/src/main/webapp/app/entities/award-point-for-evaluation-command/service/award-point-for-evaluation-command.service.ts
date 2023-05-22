import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAwardPointForEvaluationCommand, NewAwardPointForEvaluationCommand } from '../award-point-for-evaluation-command.model';

export type PartialUpdateAwardPointForEvaluationCommand = Partial<IAwardPointForEvaluationCommand> &
  Pick<IAwardPointForEvaluationCommand, 'id'>;

export type EntityResponseType = HttpResponse<IAwardPointForEvaluationCommand>;
export type EntityArrayResponseType = HttpResponse<IAwardPointForEvaluationCommand[]>;

@Injectable({ providedIn: 'root' })
export class AwardPointForEvaluationCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/award-point-for-evaluation-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(awardPointForEvaluationCommand: NewAwardPointForEvaluationCommand): Observable<EntityResponseType> {
    return this.http.post<IAwardPointForEvaluationCommand>(this.resourceUrl, awardPointForEvaluationCommand, { observe: 'response' });
  }

  update(awardPointForEvaluationCommand: IAwardPointForEvaluationCommand): Observable<EntityResponseType> {
    return this.http.put<IAwardPointForEvaluationCommand>(
      `${this.resourceUrl}/${this.getAwardPointForEvaluationCommandIdentifier(awardPointForEvaluationCommand)}`,
      awardPointForEvaluationCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(awardPointForEvaluationCommand: PartialUpdateAwardPointForEvaluationCommand): Observable<EntityResponseType> {
    return this.http.patch<IAwardPointForEvaluationCommand>(
      `${this.resourceUrl}/${this.getAwardPointForEvaluationCommandIdentifier(awardPointForEvaluationCommand)}`,
      awardPointForEvaluationCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAwardPointForEvaluationCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAwardPointForEvaluationCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAwardPointForEvaluationCommandIdentifier(awardPointForEvaluationCommand: Pick<IAwardPointForEvaluationCommand, 'id'>): number {
    return awardPointForEvaluationCommand.id;
  }

  compareAwardPointForEvaluationCommand(
    o1: Pick<IAwardPointForEvaluationCommand, 'id'> | null,
    o2: Pick<IAwardPointForEvaluationCommand, 'id'> | null
  ): boolean {
    return o1 && o2
      ? this.getAwardPointForEvaluationCommandIdentifier(o1) === this.getAwardPointForEvaluationCommandIdentifier(o2)
      : o1 === o2;
  }

  addAwardPointForEvaluationCommandToCollectionIfMissing<Type extends Pick<IAwardPointForEvaluationCommand, 'id'>>(
    awardPointForEvaluationCommandCollection: Type[],
    ...awardPointForEvaluationCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const awardPointForEvaluationCommands: Type[] = awardPointForEvaluationCommandsToCheck.filter(isPresent);
    if (awardPointForEvaluationCommands.length > 0) {
      const awardPointForEvaluationCommandCollectionIdentifiers = awardPointForEvaluationCommandCollection.map(
        awardPointForEvaluationCommandItem => this.getAwardPointForEvaluationCommandIdentifier(awardPointForEvaluationCommandItem)!
      );
      const awardPointForEvaluationCommandsToAdd = awardPointForEvaluationCommands.filter(awardPointForEvaluationCommandItem => {
        const awardPointForEvaluationCommandIdentifier =
          this.getAwardPointForEvaluationCommandIdentifier(awardPointForEvaluationCommandItem);
        if (awardPointForEvaluationCommandCollectionIdentifiers.includes(awardPointForEvaluationCommandIdentifier)) {
          return false;
        }
        awardPointForEvaluationCommandCollectionIdentifiers.push(awardPointForEvaluationCommandIdentifier);
        return true;
      });
      return [...awardPointForEvaluationCommandsToAdd, ...awardPointForEvaluationCommandCollection];
    }
    return awardPointForEvaluationCommandCollection;
  }
}
