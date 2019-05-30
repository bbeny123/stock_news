import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ResendComponent} from './resend/resend.component';
import {NewsComponent} from './news/news.component';
import {NewsDetailsComponent} from "./news-details/news-details.component";

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
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
