import { IImage } from 'empire/src/app/db_models/image';
import { IPost } from 'empire/src/app/db_models/post';
import { IAllegiance } from 'empire/src/app/db_models/allegiance';

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
