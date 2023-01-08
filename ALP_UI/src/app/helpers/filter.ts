import { Card } from '../interfaces/cards';
import { CardStatus } from '../models/card/cardStatus';

export default class Filter {
  cards: Card[];
  copyCards!: Card;
  filterBy: CardStatus;
  constructor(cards: Card[], filterBy: CardStatus) {
    this.cards = cards;
    this.filterBy = filterBy;
  }

  filter(): Card[] {
    return this.cards.filter((card) => card.status == this.filterBy);
  }
}
