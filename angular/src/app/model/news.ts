import {Comment} from './comment';

export interface News {

  id: number;
  title: string;
  description: string;
  link: string;
  user: string;
  userId: number;
  comments: Comment[];

}
