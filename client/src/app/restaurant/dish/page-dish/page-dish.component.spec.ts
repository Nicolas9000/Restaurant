import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageDishComponent } from './page-dish.component';

describe('PageDishComponent', () => {
  let component: PageDishComponent;
  let fixture: ComponentFixture<PageDishComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageDishComponent]
    });
    fixture = TestBed.createComponent(PageDishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
