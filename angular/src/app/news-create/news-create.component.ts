import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RESTService} from "../services/rest.service";
import {AlertService} from "../services/alert.service";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-news-create',
  templateUrl: './news-create.component.html',
  styleUrls: ['./news-create.component.styl']
})
export class NewsCreateComponent implements OnInit {

  editMode = 0;
  newsForm: FormGroup;

  constructor(private restService: RESTService, private alertService: AlertService, private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.newsForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(60)]],
      link: ['', [Validators.required, Validators.maxLength(1000)]],
      description: ['', [Validators.required, Validators.maxLength(300)]]
    });
  }

  submit() {
    if (this.newsForm.invalid) {
      return;
    }

    this.restService.createNews(this.newsForm.value).subscribe(r => {
        this.router.navigate(['/news/' + r.id]);
      },
      err => this.alertService.warning(err.error.message)
    );

  }

}
