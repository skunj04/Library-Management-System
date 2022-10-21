export class User{
    constructor(
        public email: string,
        public name: string,
        public gender : string,
        public dob: string,
        public address: string,
        public type: string,
        public password: string,
        public contact: number,
        public active: boolean
    )
    {}
}