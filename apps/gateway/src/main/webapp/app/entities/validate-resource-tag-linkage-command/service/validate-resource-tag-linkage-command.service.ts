import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IValidateResourceTagLinkageCommand, NewValidateResourceTagLinkageCommand } from '../validate-resource-tag-linkage-command.model';

export type PartialUpdateValidateResourceTagLinkageCommand = Partial<IValidateResourceTagLinkageCommand> &
  Pick<IValidateResourceTagLinkageCommand, 'id'>;

export type EntityResponseType = HttpResponse<IValidateResourceTagLinkageCommand>;
export type EntityArrayResponseType = HttpResponse<IValidateResourceTagLinkageCommand[]>;

@Injectable({ providedIn: 'root' })
export class ValidateResourceTagLinkageCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/validate-resource-tag-linkage-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(validateResourceTagLinkageCommand: NewValidateResourceTagLinkageCommand): Observable<EntityResponseType> {
    return this.http.post<IValidateResourceTagLinkageCommand>(this.resourceUrl, validateResourceTagLinkageCommand, { observe: 'response' });
  }

  update(validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand): Observable<EntityResponseType> {
    return this.http.put<IValidateResourceTagLinkageCommand>(
      `${this.resourceUrl}/${this.getValidateResourceTagLinkageCommandIdentifier(validateResourceTagLinkageCommand)}`,
      validateResourceTagLinkageCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(validateResourceTagLinkageCommand: PartialUpdateValidateResourceTagLinkageCommand): Observable<EntityResponseType> {
    return this.http.patch<IValidateResourceTagLinkageCommand>(
      `${this.resourceUrl}/${this.getValidateResourceTagLinkageCommandIdentifier(validateResourceTagLinkageCommand)}`,
      validateResourceTagLinkageCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IValidateResourceTagLinkageCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IValidateResourceTagLinkageCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getValidateResourceTagLinkageCommandIdentifier(
    validateResourceTagLinkageCommand: Pick<IValidateResourceTagLinkageCommand, 'id'>
  ): number {
    return validateResourceTagLinkageCommand.id;
  }

  compareValidateResourceTagLinkageCommand(
    o1: Pick<IValidateResourceTagLinkageCommand, 'id'> | null,
    o2: Pick<IValidateResourceTagLinkageCommand, 'id'> | null
  ): boolean {
    return o1 && o2
      ? this.getValidateResourceTagLinkageCommandIdentifier(o1) === this.getValidateResourceTagLinkageCommandIdentifier(o2)
      : o1 === o2;
  }

  addValidateResourceTagLinkageCommandToCollectionIfMissing<Type extends Pick<IValidateResourceTagLinkageCommand, 'id'>>(
    validateResourceTagLinkageCommandCollection: Type[],
    ...validateResourceTagLinkageCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const validateResourceTagLinkageCommands: Type[] = validateResourceTagLinkageCommandsToCheck.filter(isPresent);
    if (validateResourceTagLinkageCommands.length > 0) {
      const validateResourceTagLinkageCommandCollectionIdentifiers = validateResourceTagLinkageCommandCollection.map(
        validateResourceTagLinkageCommandItem => this.getValidateResourceTagLinkageCommandIdentifier(validateResourceTagLinkageCommandItem)!
      );
      const validateResourceTagLinkageCommandsToAdd = validateResourceTagLinkageCommands.filter(validateResourceTagLinkageCommandItem => {
        const validateResourceTagLinkageCommandIdentifier = this.getValidateResourceTagLinkageCommandIdentifier(
          validateResourceTagLinkageCommandItem
        );
        if (validateResourceTagLinkageCommandCollectionIdentifiers.includes(validateResourceTagLinkageCommandIdentifier)) {
          return false;
        }
        validateResourceTagLinkageCommandCollectionIdentifiers.push(validateResourceTagLinkageCommandIdentifier);
        return true;
      });
      return [...validateResourceTagLinkageCommandsToAdd, ...validateResourceTagLinkageCommandCollection];
    }
    return validateResourceTagLinkageCommandCollection;
  }
}
