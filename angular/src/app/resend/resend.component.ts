import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-resend',
  templateUrl: './resend.component.html',
  styleUrls: ['./resend.component.styl']
})
export class ResendComponent implements OnInit {

  constructor(private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit() {
    const token = this.route.snapshot.queryParamMap.get('token');

    this.authService.activate(token).subscribe(() => alert('done'), err => alert(err.error.message));
  }

}
