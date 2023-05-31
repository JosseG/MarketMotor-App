import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerarventaComponent } from './generarventa.component';

describe('GenerarventaComponent', () => {
  let component: GenerarventaComponent;
  let fixture: ComponentFixture<GenerarventaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerarventaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenerarventaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
