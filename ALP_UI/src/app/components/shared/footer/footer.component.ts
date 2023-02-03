import { Component } from '@angular/core';

@Component({
  selector: 'alp-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css'],
})
export class FooterComponent {
  currentYear = new Date().getFullYear();
}
