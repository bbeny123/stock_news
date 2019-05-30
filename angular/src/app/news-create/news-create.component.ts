import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RESTService} from "../services/rest.service";
import {AlertService} from "../services/alert.service";
import {AuthService} from "../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {News} from "../model/news";

@Component({
  selector: 'app-news-create',
  templateUrl: './news-create.component.html',
  styleUrls: ['./news-create.component.styl']
})
export class NewsCreateComponent implements OnInit {

  id: string;
  news: News;
  newsForm: FormGroup;

  constructor(private route: ActivatedRoute, private restService: RESTService, private alertService: AlertService, private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.newsForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(60)]],
      link: ['', [Validators.required, Validators.maxLength(1000)]],
      description: ['', [Validators.required, Validators.maxLength(300)]]
    });

    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      this.restService.getNews(this.id).subscribe(
        r => {
          this.newsForm.patchValue(r);
          this.news = r;
        },
        e => this.alertService.warning(e.error.message)
      );
    }
  }

  submit() {
    if (this.newsForm.invalid) {
      return;
    }

    this.restService.createOrUpdateNews(this.newsForm.value, this.id).subscribe(r => {
        this.router.navigate(['/news/' + r.id]);
      },
      err => this.alertService.warning(err.error.message)
    );
  }

  reset() {
    this.newsForm.patchValue(this.news);
  }

}
