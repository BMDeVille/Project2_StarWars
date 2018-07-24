import { IUser } from './user';

export class IComment {
    cid: number;
    body: string;
    postid: number;
    ownerid: number;
    likes: IUser[];

    constructor(comment: IComment) {
       this.cid = comment.cid;
       this.body = comment.body;
       this.postid = comment.postid;
       this.ownerid = comment.ownerid;
       this.likes = comment.likes;
    }
}

