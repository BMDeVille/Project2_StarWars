import { IComment } from './comment';
import { IUser } from './user';
import { IImage } from './image';

export interface IPost {
    pid: number;
    body: string;
    youtube: string;
    created: Date;
    comments: IComment[];
    likes: IUser[];
    images: IImage[];
}
