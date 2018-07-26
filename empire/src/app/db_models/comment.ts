import { IUser } from './user';
import { IPost } from './post';

export class IComment {
    cid: number;
    body: string;
    poster: IUser;
    likes: IUser[];
    post: IPost;

    constructor(comment: IComment) {
       this.cid = comment.cid;
       this.body = comment.body;
       this.poster = new IUser(comment.poster);
       this.likes = comment.likes;
       this.post = new IPost(comment.post);
    }
}

