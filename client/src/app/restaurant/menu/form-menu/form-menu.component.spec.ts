import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormMenuComponent } from './form-menu.component';

describe('FormMenuComponent', () => {
  let component: FormMenuComponent;
  let fixture: ComponentFixture<FormMenuComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormMenuComponent]
    });
    fixture = TestBed.createComponent(FormMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
