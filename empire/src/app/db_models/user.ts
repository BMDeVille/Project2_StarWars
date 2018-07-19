<<<<<<< HEAD
import { IImage } from '../db_models/image';
import { IPost } from '../db_models/post';
import { IAllegiance } from '../db_models/allegiance';
=======
import { IImage } from './image';
import { IPost } from './post';
import { IAllegiance } from './allegiance';
>>>>>>> 553562bc36b1ceb5b19609e9e7e090e49451641c

export class IUser {
    id: number;
    username: string;
    fname: string;
    lname: string;
    email: string;
    dob: Date;
    sec_ans: string;
    about: string;
    image: IImage;
    posts: IPost[];
    followers: IUser[];
    allegiance: IAllegiance;

    constructor (user: IUser) {
        this.id = user.id;
        this.username = user.username;
        this.fname = user.fname;
        this.lname = user.lname;
        this.email = user.email;
        this.dob = user.dob;
        this.sec_ans = this.sec_ans;
        this.about = user.about;
        this.image = user.image;
        this.posts = user.posts;
        this.followers = user.followers;
        this.allegiance = user.allegiance;
    }
}
