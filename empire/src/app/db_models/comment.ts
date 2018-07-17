import { IUser } from './user';

export class IComment {
    cid: number;
    body: string;
    likes: IUser[];

    constructor(comment: IComment) {
       this.cid = comment.cid;
       this.body = comment.body;
       this.likes = comment.likes;
    }
}

