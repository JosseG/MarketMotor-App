import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpleadoupdateComponent } from './empleadoupdate.component';

describe('EmpleadoupdateComponent', () => {
  let component: EmpleadoupdateComponent;
  let fixture: ComponentFixture<EmpleadoupdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpleadoupdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpleadoupdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
