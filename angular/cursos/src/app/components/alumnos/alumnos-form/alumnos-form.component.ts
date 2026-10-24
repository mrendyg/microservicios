import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Alumno } from '../../../models/alumno';
import { AlumnoService } from '../../../services/alumno.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  imports: [CommonModule, RouterLink, FormsModule],
})
export class AlumnosFormComponent implements OnInit {

  titulo = "Crear alumno"
  alumno: Alumno = new Alumno();
  error: any;

  constructor(
    private service: AlumnoService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) { }


  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const id: number = +(params.get('id') || 0);
      if (id) {
        this.service.ver(id).subscribe(alumno => {
          this.alumno = alumno;
          this.cdr.detectChanges();
        })
      }
    });

  }

  public crear(): void {
    this.service.crear(this.alumno).subscribe(alumno => {
      console.log(this.alumno);
      alert(`Alumno ${alumno.nombre} creado con exito`)
      this.router.navigate(['/alumnos']);
    }, err => {
      if (err.status === 400) {
        this.error = err.error;
        console.log(this.error);

      }
    });
  }

  public editar(): void {
    this.service.editar(this.alumno).subscribe(alumno => {
      console.log(this.alumno);
      alert(`Alumno ${alumno.nombre} actualizado con exito`)
      this.router.navigate(['/alumnos']);
    }, err => {
      if (err.status === 400) {
        this.error = err.error;
        console.log(this.error);
        this.cdr.detectChanges();
      }
    });
  }


}
