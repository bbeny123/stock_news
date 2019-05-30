import {Component, OnInit} from '@angular/core';
import {News} from "../model/news";

@Component({
  selector: 'app-news-details',
  templateUrl: './news-details.component.html',
  styleUrls: ['./news-details.component.styl']
})
export class NewsDetailsComponent implements OnInit {

  news: News;

  constructor() { }

  ngOnInit() {
  }

}
