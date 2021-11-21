import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PresentacionCursoComponent } from './presentacion-curso.component';

describe('PresentacionCursoComponent', () => {
  let component: PresentacionCursoComponent;
  let fixture: ComponentFixture<PresentacionCursoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PresentacionCursoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PresentacionCursoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
