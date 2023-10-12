import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormDishComponent } from './form-dish.component';

describe('FormDishComponent', () => {
  let component: FormDishComponent;
  let fixture: ComponentFixture<FormDishComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormDishComponent]
    });
    fixture = TestBed.createComponent(FormDishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
