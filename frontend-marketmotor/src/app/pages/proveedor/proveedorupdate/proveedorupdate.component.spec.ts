import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedorupdateComponent } from './proveedorupdate.component';

describe('ProveedorupdateComponent', () => {
  let component: ProveedorupdateComponent;
  let fixture: ComponentFixture<ProveedorupdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProveedorupdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProveedorupdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
