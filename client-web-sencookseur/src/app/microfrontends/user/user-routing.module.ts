import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserListComponent} from "./components/user-list/user-list.component";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  { path: '', component: UserListComponent },
  // Ajoutez d'autres routes ici si nécessaire
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class UserRoutingModule { }
