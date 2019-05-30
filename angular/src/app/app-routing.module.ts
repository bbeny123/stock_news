import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ResendComponent} from './resend/resend.component';
import {NewsComponent} from './news/news.component';

const routes: Routes = [
  {
    path: '',
    component: NewsComponent
  },
  {
    path: 'register/activate',
    component: ResendComponent
  },
  // {
  //   path: '**',
  //   component: PageNotFoundComponent
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
