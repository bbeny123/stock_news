import {Component, OnInit} from '@angular/core';
import {News} from '../model/news';
import {RESTService} from '../services/rest.service';
import {AlertService} from '../services/alert.service';
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.styl']
})
export class NewsComponent implements OnInit {

  page = 1;
  size = 0;
  newses: News[] = [];

  constructor(private restService: RESTService, private alertService: AlertService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.pageChange();
  }

  pageChange() {
    this.restService.getNewsList(this.page).subscribe(r => {
        this.size = r.totalItems;
        this.newses = r.items;
      }, e => this.alertService.warning(e.error.message)
    );
  }

  select(news: News) {
    this.alertService.closeAlert();
    this.router.navigate(['/news/' + news.id]);
  }

  edit(news: News) {
    this.alertService.closeAlert();
    this.router.navigate(['/edit/' + news.id]);
  }

  remove(news: News) {
    this.alertService.closeAlert();
    this.restService.removeNews(news.id).subscribe(r => {
        this.newses.splice(this.newses.indexOf(news), 1);
      },
      err => this.alertService.warning(err.error.message)
    );
  }

}
