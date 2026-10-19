import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';


@NgModule({
  declarations: [],
  exports: [NavbarComponent],
  imports: [CommonModule, NavbarComponent],
})
export class LayoutModule { }
