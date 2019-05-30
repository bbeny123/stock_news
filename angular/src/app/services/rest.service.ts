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

  private options = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }
    )
  };

  constructor(private http: HttpClient) {}

  getNewsList(page: number = 1) {
    const options = {
      headers: this.getOptions().headers,
      params: new HttpParams().set('page', (page - 1) + '')
    };

    return this.http.get<NewsList>(AppConfig.ENDPOINT_NEWS, options);
  }

  getNews(id: string) {
    return this.http.get<News>(AppConfig.ENDPOINT_NEWS + "/" + id, this.getOptions());
  }

  createOrUpdateNews(news: NewsRequest, id?: string) {
    return id ? this.updateNews(news, id) : this.createNews(news);
  }

  createNews(news: NewsRequest) {
    return this.http.post<News>(AppConfig.ENDPOINT_NEWS, news, this.getOptions());
  }

  removeNews(id: number) {
    return this.http.delete<any>(AppConfig.ENDPOINT_NEWS + '/' + id, this.getOptions());
  }

  updateNews(news: NewsRequest, id: string) {
    return this.http.patch<News>(AppConfig.ENDPOINT_NEWS + '/' + id, news, this.getOptions());
  }

  addComment(comment: CommentRequest) {
    return this.http.post<Comment>(AppConfig.ENDPOINT_COMMENT, comment, this.getOptions());
  }

  removeComment(id: number) {
    return this.http.delete<any>(AppConfig.ENDPOINT_COMMENT + '/' + id, this.getOptions());
  }

  private getOptions() {
    const token = localStorage.getItem('token');
    return token ? {headers: this.options.headers.append('Authorization', 'Bearer ' + JSON.parse(token).token)} : this.options;
  }

}
