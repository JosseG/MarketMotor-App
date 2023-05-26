import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedorinsertComponent } from './proveedorinsert.component';

describe('ProveedorinsertComponent', () => {
  let component: ProveedorinsertComponent;
  let fixture: ComponentFixture<ProveedorinsertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProveedorinsertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProveedorinsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
