import { Card } from "src/app/interfaces/cards";

export class Column {
    constructor(public name: string, public id: string, public tasks: Card[]) {}
  }
  