import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehicleListComponent } from '../components/vehicle-list/vehicle-list.component';
import { VehicleRoutingModule } from '../vehicle-routing/vehicle-routing.module';



@NgModule({
  declarations: [
    VehicleListComponent
  ],
  imports: [
    CommonModule,
    VehicleRoutingModule
  ]
})
export class VehicleModule { }
