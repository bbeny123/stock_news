import {Component, OnInit} from '@angular/core';
import {News} from '../model/news';
import {ActivatedRoute, Router} from "@angular/router";
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

  constructor(private route: ActivatedRoute, private restService: RESTService, private alertService: AlertService, private authService: AuthService, private formBuilder: FormBuilder, private router: Router) {  }

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

    this.alertService.closeAlert();

    this.restService.addComment(this.cmtForm.value).subscribe(r => {
        this.cmtForm.reset({newsId: this.id});
        this.news.comments.push(r)
      },
      err => this.alertService.warning(err.error.message)
    );
  }

  edit(news: News) {
    this.alertService.closeAlert();
    this.router.navigate(['/edit/' + news.id]);
  }

  remove(news: News) {
    this.alertService.closeAlert();
    this.restService.removeNews(news.id).subscribe(() => {
        this.alertService.success('The news has been removed');
        this.router.navigate(['/']);
      },
      err => this.alertService.warning(err.error.message)
    );
  }

}
