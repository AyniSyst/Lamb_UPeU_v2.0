import { Component, computed, inject, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { map } from 'rxjs';
import { MICROSERVICIOS } from './ms-catalogo';
import { Recurso } from './ms.model';
import { RecursoCrud } from './recurso-crud';

/** Una pagina por microservicio: sus recursos a la izquierda y el CRUD del elegido a la derecha. */
@Component({
  selector: 'app-microservicio-page',
  imports: [RecursoCrud, RouterLink],
  templateUrl: './microservicio-page.html',
  styleUrl: './microservicio-page.css',
})
export class MicroservicioPage {
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  private readonly clave = toSignal(this.route.paramMap.pipe(map((p) => p.get('clave') ?? '')), { initialValue: '' });
  private readonly recursoElegido = toSignal(this.route.queryParamMap.pipe(map((q) => q.get('r'))), { initialValue: null });
  protected readonly busqueda = signal('');

  protected readonly ms = computed(() => MICROSERVICIOS.find((m) => m.clave === this.clave()) ?? null);

  protected readonly grupos = computed(() => {
    const texto = this.busqueda().trim().toLowerCase();
    const grupos = new Map<string, Recurso[]>();
    for (const r of this.ms()?.recursos ?? []) {
      if (texto && !`${r.etiqueta} ${r.grupo} ${r.ruta}`.toLowerCase().includes(texto)) {
        continue;
      }
      grupos.set(r.grupo, [...(grupos.get(r.grupo) ?? []), r]);
    }
    return [...grupos.entries()].map(([nombre, recursos]) => ({ nombre, recursos }));
  });

  protected readonly recurso = computed(() => {
    const recursos = this.ms()?.recursos ?? [];
    return recursos.find((r) => r.clave === this.recursoElegido()) ?? recursos[0] ?? null;
  });

  protected elegir(recurso: Recurso): void {
    this.router.navigate([], { queryParams: { r: recurso.clave }, relativeTo: this.route });
  }
}
