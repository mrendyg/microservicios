import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLinkWithHref, RouterLinkActive } from '@angular/router';
import { AlumnosComponent } from './components/alumnos/alumnos.component';
import { LayoutModule } from './layout/layout-module';

@Component({
  imports: [RouterOutlet, RouterLinkActive, RouterLinkWithHref, LayoutModule],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('cursos');
}
