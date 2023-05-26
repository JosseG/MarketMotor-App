import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpleadoinsertComponent } from './empleadoinsert.component';

describe('EmpleadoinsertComponent', () => {
  let component: EmpleadoinsertComponent;
  let fixture: ComponentFixture<EmpleadoinsertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpleadoinsertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpleadoinsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
