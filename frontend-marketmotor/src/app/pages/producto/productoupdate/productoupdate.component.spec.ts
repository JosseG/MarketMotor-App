import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductoupdateComponent } from './productoupdate.component';

describe('ProductoupdateComponent', () => {
  let component: ProductoupdateComponent;
  let fixture: ComponentFixture<ProductoupdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductoupdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductoupdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
