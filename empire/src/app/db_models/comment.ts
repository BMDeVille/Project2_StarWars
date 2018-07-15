import { IUser } from './user';

export interface IComment {
    cid: number;
    body: string;
    likes: IUser[];
}
