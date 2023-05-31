import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerarordencompraComponent } from './generarordencompra.component';

describe('GenerarordencompraComponent', () => {
  let component: GenerarordencompraComponent;
  let fixture: ComponentFixture<GenerarordencompraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerarordencompraComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenerarordencompraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
