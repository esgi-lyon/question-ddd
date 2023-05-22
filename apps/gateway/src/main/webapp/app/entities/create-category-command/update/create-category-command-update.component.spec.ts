import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CreateCategoryCommandFormService } from './create-category-command-form.service';
import { CreateCategoryCommandService } from '../service/create-category-command.service';
import { ICreateCategoryCommand } from '../create-category-command.model';
import { ICategory } from 'app/entities/SkillContext/category/category.model';
import { CategoryService } from 'app/entities/SkillContext/category/service/category.service';

import { CreateCategoryCommandUpdateComponent } from './create-category-command-update.component';

describe('CreateCategoryCommand Management Update Component', () => {
  let comp: CreateCategoryCommandUpdateComponent;
  let fixture: ComponentFixture<CreateCategoryCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let createCategoryCommandFormService: CreateCategoryCommandFormService;
  let createCategoryCommandService: CreateCategoryCommandService;
  let categoryService: CategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CreateCategoryCommandUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(CreateCategoryCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreateCategoryCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    createCategoryCommandFormService = TestBed.inject(CreateCategoryCommandFormService);
    createCategoryCommandService = TestBed.inject(CreateCategoryCommandService);
    categoryService = TestBed.inject(CategoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Category query and add missing value', () => {
      const createCategoryCommand: ICreateCategoryCommand = { id: 456 };
      const category: ICategory = { id: 50644 };
      createCategoryCommand.category = category;

      const categoryCollection: ICategory[] = [{ id: 88804 }];
      jest.spyOn(categoryService, 'query').mockReturnValue(of(new HttpResponse({ body: categoryCollection })));
      const additionalCategories = [category];
      const expectedCollection: ICategory[] = [...additionalCategories, ...categoryCollection];
      jest.spyOn(categoryService, 'addCategoryToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ createCategoryCommand });
      comp.ngOnInit();

      expect(categoryService.query).toHaveBeenCalled();
      expect(categoryService.addCategoryToCollectionIfMissing).toHaveBeenCalledWith(
        categoryCollection,
        ...additionalCategories.map(expect.objectContaining)
      );
      expect(comp.categoriesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const createCategoryCommand: ICreateCategoryCommand = { id: 456 };
      const category: ICategory = { id: 16110 };
      createCategoryCommand.category = category;

      activatedRoute.data = of({ createCategoryCommand });
      comp.ngOnInit();

      expect(comp.categoriesSharedCollection).toContain(category);
      expect(comp.createCategoryCommand).toEqual(createCategoryCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateCategoryCommand>>();
      const createCategoryCommand = { id: 123 };
      jest.spyOn(createCategoryCommandFormService, 'getCreateCategoryCommand').mockReturnValue(createCategoryCommand);
      jest.spyOn(createCategoryCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createCategoryCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createCategoryCommand }));
      saveSubject.complete();

      // THEN
      expect(createCategoryCommandFormService.getCreateCategoryCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(createCategoryCommandService.update).toHaveBeenCalledWith(expect.objectContaining(createCategoryCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateCategoryCommand>>();
      const createCategoryCommand = { id: 123 };
      jest.spyOn(createCategoryCommandFormService, 'getCreateCategoryCommand').mockReturnValue({ id: null });
      jest.spyOn(createCategoryCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createCategoryCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createCategoryCommand }));
      saveSubject.complete();

      // THEN
      expect(createCategoryCommandFormService.getCreateCategoryCommand).toHaveBeenCalled();
      expect(createCategoryCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateCategoryCommand>>();
      const createCategoryCommand = { id: 123 };
      jest.spyOn(createCategoryCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createCategoryCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(createCategoryCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCategory', () => {
      it('Should forward to categoryService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(categoryService, 'compareCategory');
        comp.compareCategory(entity, entity2);
        expect(categoryService.compareCategory).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
