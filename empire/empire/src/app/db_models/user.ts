import { IPost } from './post';
import { IImage } from './image';

export interface IUser {
    id: number;
    username: string;
    fname: string;
    lname: string;
    email: string;
    password: string;
    dob: Date;
    sec_ans: string;
    about: string;
    image: IImage;
    posts: IPost[];
    followers: IUser[];
    allegiance: number;
}
