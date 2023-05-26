import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedorlistComponent } from './proveedorlist.component';

describe('ProveedorlistComponent', () => {
  let component: ProveedorlistComponent;
  let fixture: ComponentFixture<ProveedorlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProveedorlistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProveedorlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
