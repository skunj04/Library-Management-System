export interface IssuedBooks{
    id:string,
    email: string,
    name: string,
    bookname: string,
    isbn: number,
    isReturned: boolean,
    returnDate: Date
}