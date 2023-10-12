import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRestaurantComponent } from './form-restaurant.component';

describe('FormRestaurantComponent', () => {
  let component: FormRestaurantComponent;
  let fixture: ComponentFixture<FormRestaurantComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormRestaurantComponent]
    });
    fixture = TestBed.createComponent(FormRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
