import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/interfaces/cards';

@Component({
  selector: '[plt-card]',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit{
  
   @Input('plt-card') inData!: Card;

  ngOnInit(): void {
      // console.log(this.inData);
    }
}
