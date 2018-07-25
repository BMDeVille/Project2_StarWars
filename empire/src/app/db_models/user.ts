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
    joined: Date;
    sec_ques: string;
    securityAnswer: string;
    about: string;
    image: IImage;
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
        this.joined = user.joined;
        this.sec_ques = user.sec_ques;
        this.securityAnswer = user.securityAnswer;
        this.about = user.about;
        this.image = user.image;
        this.followers = user.followers;
        this.allegiance = user.allegiance;
    }
}
