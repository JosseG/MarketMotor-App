import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteordencompraComponent } from './reporteordencompra.component';

describe('ReporteordencompraComponent', () => {
  let component: ReporteordencompraComponent;
  let fixture: ComponentFixture<ReporteordencompraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReporteordencompraComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteordencompraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
