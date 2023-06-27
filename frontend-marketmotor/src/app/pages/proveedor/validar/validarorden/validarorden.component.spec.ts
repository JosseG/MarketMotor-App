import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidarordenComponent } from './validarorden.component';

describe('ValidarordenComponent', () => {
  let component: ValidarordenComponent;
  let fixture: ComponentFixture<ValidarordenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ValidarordenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ValidarordenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
