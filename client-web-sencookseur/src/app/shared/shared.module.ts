import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComponentsComponent } from './components/components.component';
import { DirectivesDirective } from './directives.directive';
import { PipesPipe } from './pipes.pipe';



@NgModule({
  declarations: [
    ComponentsComponent,
    DirectivesDirective,
    PipesPipe
  ],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
