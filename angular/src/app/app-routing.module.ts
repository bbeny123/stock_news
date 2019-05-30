import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ResendComponent} from "./resend/resend.component";

const routes: Routes = [
  {
    path: 'register/activate',
    component: ResendComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
