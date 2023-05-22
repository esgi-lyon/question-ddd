import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAnsweredTag, NewAnsweredTag } from '../answered-tag.model';

export type PartialUpdateAnsweredTag = Partial<IAnsweredTag> & Pick<IAnsweredTag, 'id'>;

export type EntityResponseType = HttpResponse<IAnsweredTag>;
export type EntityArrayResponseType = HttpResponse<IAnsweredTag[]>;

@Injectable({ providedIn: 'root' })
export class AnsweredTagService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/answered-tags');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnsweredTag>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnsweredTag[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getAnsweredTagIdentifier(answeredTag: Pick<IAnsweredTag, 'id'>): number {
    return answeredTag.id;
  }

  compareAnsweredTag(o1: Pick<IAnsweredTag, 'id'> | null, o2: Pick<IAnsweredTag, 'id'> | null): boolean {
    return o1 && o2 ? this.getAnsweredTagIdentifier(o1) === this.getAnsweredTagIdentifier(o2) : o1 === o2;
  }

  addAnsweredTagToCollectionIfMissing<Type extends Pick<IAnsweredTag, 'id'>>(
    answeredTagCollection: Type[],
    ...answeredTagsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const answeredTags: Type[] = answeredTagsToCheck.filter(isPresent);
    if (answeredTags.length > 0) {
      const answeredTagCollectionIdentifiers = answeredTagCollection.map(
        answeredTagItem => this.getAnsweredTagIdentifier(answeredTagItem)!
      );
      const answeredTagsToAdd = answeredTags.filter(answeredTagItem => {
        const answeredTagIdentifier = this.getAnsweredTagIdentifier(answeredTagItem);
        if (answeredTagCollectionIdentifiers.includes(answeredTagIdentifier)) {
          return false;
        }
        answeredTagCollectionIdentifiers.push(answeredTagIdentifier);
        return true;
      });
      return [...answeredTagsToAdd, ...answeredTagCollection];
    }
    return answeredTagCollection;
  }
}
