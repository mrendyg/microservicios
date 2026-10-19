import { Component, OnInit } from '@angular/core';
import { RouterLinkActive, RouterLinkWithHref, RouterOutlet } from '@angular/router';
import { LayoutModule } from '../layout-module';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  imports: [RouterLinkActive, RouterLinkWithHref],
})
export class NavbarComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    
  }

}

