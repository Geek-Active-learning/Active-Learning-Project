import { Users } from "./users";

export interface UsersApi extends Users{
    users :Users[];
    totalCount:number;
}