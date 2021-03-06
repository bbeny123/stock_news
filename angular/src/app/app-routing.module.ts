import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ResendComponent} from './resend/resend.component';
import {NewsComponent} from './news/news.component';
import {NewsDetailsComponent} from "./news-details/news-details.component";
import {NewsCreateUpdateComponent} from "./news-create-update/news-create-update.component";
import {AuthAdminGuard} from "./services/auth-admin.guard";

const routes: Routes = [
  {
    path: '',
    component: NewsComponent
  },
  {
    path: 'register/activate',
    component: ResendComponent
  },
  {
    path: 'news/:id',
    component: NewsDetailsComponent
  },
  {
    path: 'create',
    component: NewsCreateUpdateComponent,
    canActivate: [AuthAdminGuard]
  },
  {
    path: 'edit/:id',
    component: NewsCreateUpdateComponent,
    canActivate: [AuthAdminGuard]
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
