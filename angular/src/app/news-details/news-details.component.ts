import {Component, OnInit} from '@angular/core';
import {News} from '../model/news';
import {ActivatedRoute} from "@angular/router";
import {RESTService} from "../services/rest.service";
import {AlertService} from "../services/alert.service";
import {AuthService} from "../services/auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-news-details',
  templateUrl: './news-details.component.html',
  styleUrls: ['./news-details.component.styl']
})
export class NewsDetailsComponent implements OnInit {

  error = false;
  id: string;
  news: News;
  cmtForm: FormGroup;

  constructor(private route: ActivatedRoute, private restService: RESTService, private alertService: AlertService, private authService: AuthService, private formBuilder: FormBuilder) {  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.restService.getNews(this.id).subscribe(r => this.news = r,
      e => {
        this.error = true;
        this.alertService.warning(e.error.message)
      });

    this.cmtForm = this.formBuilder.group({
      content: ['', [Validators.required, Validators.maxLength(600)]],
      newsId: [this.id]
    });
  }

  addComment() {
    if (this.cmtForm.invalid) {
      return;
    }

    this.restService.addComment(this.cmtForm.value).subscribe(r => {
        this.cmtForm.reset({newsId: this.id});
        this.news.comments.push(r)
      },
      err => this.alertService.warning(err.error.message)
    );
  }

}
