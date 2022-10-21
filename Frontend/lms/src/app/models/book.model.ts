export class Book{
    constructor(
    public isbn: number,
    public name: string,
    public description: string,
    public authorName: string,
    public category: string,
    public quantity: number
    )
    {}
}