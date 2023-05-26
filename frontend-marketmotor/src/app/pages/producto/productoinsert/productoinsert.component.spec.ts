import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductoinsertComponent } from './productoinsert.component';

describe('ProductoinsertComponent', () => {
  let component: ProductoinsertComponent;
  let fixture: ComponentFixture<ProductoinsertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductoinsertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductoinsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
