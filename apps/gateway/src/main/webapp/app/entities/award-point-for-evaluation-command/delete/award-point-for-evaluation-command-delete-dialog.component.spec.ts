jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { AwardPointForEvaluationCommandService } from '../service/award-point-for-evaluation-command.service';

import { AwardPointForEvaluationCommandDeleteDialogComponent } from './award-point-for-evaluation-command-delete-dialog.component';

describe('AwardPointForEvaluationCommand Management Delete Component', () => {
  let comp: AwardPointForEvaluationCommandDeleteDialogComponent;
  let fixture: ComponentFixture<AwardPointForEvaluationCommandDeleteDialogComponent>;
  let service: AwardPointForEvaluationCommandService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [AwardPointForEvaluationCommandDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(AwardPointForEvaluationCommandDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AwardPointForEvaluationCommandDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AwardPointForEvaluationCommandService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      })
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
