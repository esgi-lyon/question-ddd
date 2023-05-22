jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { TagChoicesListCommandService } from '../service/tag-choices-list-command.service';

import { TagChoicesListCommandDeleteDialogComponent } from './tag-choices-list-command-delete-dialog.component';

describe('TagChoicesListCommand Management Delete Component', () => {
  let comp: TagChoicesListCommandDeleteDialogComponent;
  let fixture: ComponentFixture<TagChoicesListCommandDeleteDialogComponent>;
  let service: TagChoicesListCommandService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [TagChoicesListCommandDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(TagChoicesListCommandDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TagChoicesListCommandDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TagChoicesListCommandService);
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
