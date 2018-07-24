import { IImage } from './image';
import { IPost } from './post';
import { IAllegiance } from './allegiance';

export class IUser {
    id: number;
    username: string;
    password: string;
    fname: string;
    lname: string;
    email: string;
    dob: Date;
    sec_ans: string;
    sec_ques: string;
    about: string;
    image: IImage;
    posts: IPost[];
    followers: IUser[];
    allegiance: IAllegiance;

    constructor (user: IUser) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.fname = user.fname;
        this.lname = user.lname;
        this.email = user.email;
        this.dob = user.dob;
        this.sec_ans = user.sec_ans;
        this.sec_ques = user.sec_ques;
        this.about = user.about;
        this.image = user.image;
        this.posts = user.posts;
        this.followers = user.followers;
        this.allegiance = user.allegiance;
    }
}
