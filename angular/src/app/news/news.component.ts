import {Component, OnInit} from '@angular/core';
import {News} from '../model/news';
import {RESTService} from '../services/rest.service';
import {AlertService} from '../services/alert.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.styl']
})
export class NewsComponent implements OnInit {

  page = 1;
  size = 0;
  newses: News[] = [];

  constructor(private restService: RESTService, private alertService: AlertService) { }

  ngOnInit() {
    this.pageChange();
  }

  pageChange() {
    this.restService.getNewsList(this.page).subscribe(r => {
      this.size = r.totalItems;
      this.newses = r.items;
    }, err => {
      this.alertService.warning(err.error.message);
    });
  }

  select(news: News) {
    console.log(news);
  }

}
