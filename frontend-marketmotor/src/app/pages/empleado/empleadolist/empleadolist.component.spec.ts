import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpleadolistComponent } from './empleadolist.component';

describe('EmpleadolistComponent', () => {
  let component: EmpleadolistComponent;
  let fixture: ComponentFixture<EmpleadolistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpleadolistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpleadolistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
