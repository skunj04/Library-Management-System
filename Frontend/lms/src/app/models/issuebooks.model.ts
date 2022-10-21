import { Book } from "./book.model";
import { User } from "./user.model";

export class IssueBooks{
    constructor(
        public Users: User[],
        public Books: Book[]
    )
    {}
}