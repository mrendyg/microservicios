import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { AlumnoService } from '../../services/alumno.service';
import { Alumno } from '../../models/alumno';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  imports: [CommonModule, RouterLink],
})
export class AlumnosComponent implements OnInit {

  titulo = 'Listado de alumnos';
  alumnos!: Alumno[];

  constructor(private service: AlumnoService, private cdr: ChangeDetectorRef) { }

  ngOnInit() {
    this.service.listar().subscribe(alumnos => {
      this.alumnos = alumnos;
      this.cdr.detectChanges();
    });
  }

}
