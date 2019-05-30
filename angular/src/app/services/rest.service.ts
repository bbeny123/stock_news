import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {AppConfig} from '../app-config';
import {NewsList} from '../model/news-list';
import {News} from "../model/news";
import {CommentRequest} from "../model/comment-request";
import {Comment} from "../model/comment";
import {NewsRequest} from "../model/news-request";

@Injectable({
  providedIn: 'root'
})
export class RESTService {

  private token = localStorage.getItem('token');
  private options = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': this.token != null ? 'Bearer ' + JSON.parse(this.token).token : ''
      }
    )
  };

  constructor(private http: HttpClient) {}

  getNewsList(page: number = 1) {
    const options = {
      headers: this.options.headers,
      params: new HttpParams().set('page', (page - 1) + '')
    };

    return this.http.get<NewsList>(AppConfig.ENDPOINT_NEWS, options);
  }

  getNews(id: string) {
    return this.http.get<News>(AppConfig.ENDPOINT_NEWS + "/" + id, this.options);
  }

  createNews(news: NewsRequest) {
    return this.http.post<News>(AppConfig.ENDPOINT_NEWS, news, this.options);
  }

  addComment(comment: CommentRequest) {
    return this.http.post<Comment>(AppConfig.ENDPOINT_COMMENT, comment, this.options);
  }

}
