import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-advert',
  templateUrl: './header-advert.element.html',
  styleUrls: ['./header-advert.element.css']
})
export class HeaderAdvertElement implements OnInit {
  text = 'Raklamı Kapat';
  close = false;
  constructor() { }

  ngOnInit() {
  }

  changeText() {
    this.close = !this.close;

    if (this.close) {
      this.text = 'Reklamı aç';
    }
    else {
      this.text = 'Reklamı kapat';
    }
  }
}
