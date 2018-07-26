import { IComment } from './comment';
import { IUser } from './user';

export class IPost {
    pid: number;
    body: string;
    youtube: string;
    created: Date;
    creator: IUser;
    comments: IComment[];
    likes: IUser[];
    images: string[];

    constructor(post: IPost) {
        this.pid = post.pid;
        this.body = post.body;
        this.youtube = post.youtube;
        this.created = post.created;
        this.creator = new IUser(post.creator);
        this.comments = [];
        this.likes = post.likes;
        this.images = post.images;
    }
}
