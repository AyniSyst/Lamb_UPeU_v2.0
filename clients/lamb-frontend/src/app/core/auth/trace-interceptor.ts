import { HttpInterceptorFn } from '@angular/common/http';

export const traceInterceptor: HttpInterceptorFn = (req, next) => {
  const peticion = req.clone({ setHeaders: { 'X-Trace-ID': crypto.randomUUID() } });
  return next(peticion);
};
