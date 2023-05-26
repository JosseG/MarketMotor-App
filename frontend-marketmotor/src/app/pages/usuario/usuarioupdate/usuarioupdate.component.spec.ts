import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioupdateComponent } from './usuarioupdate.component';

describe('UsuarioupdateComponent', () => {
  let component: UsuarioupdateComponent;
  let fixture: ComponentFixture<UsuarioupdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioupdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioupdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
