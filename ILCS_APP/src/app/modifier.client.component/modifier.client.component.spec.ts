import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierClientComponent } from './modifier.client.component';

describe('ModifierClientComponent', () => {
  let component: ModifierClientComponent;
  let fixture: ComponentFixture<ModifierClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifierClientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModifierClientComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
