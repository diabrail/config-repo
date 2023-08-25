import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CommonModule} from "@angular/common";

const routes: Routes = [
  { path: '', redirectTo: '/user', pathMatch: 'full' },
  { path: 'user', loadChildren: () => import('./user/user.module').then(m => m.UserModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes),CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
