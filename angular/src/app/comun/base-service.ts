import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable()
export abstract class BaseService {

  protected abstract  URL_SERVICIO: string;

  constructor(
    protected http: HttpClient
  ) {
  }

  protected construirURL(url: string): string {
    if (url.trim().length === 0) {
      return `${environment.BASE_URL}/${this.URL_SERVICIO}`;
    } else {
      return `${environment.BASE_URL}/${this.URL_SERVICIO}/${url}`;
    }
  }

  protected construirPeticionGet(url: string): any {
    return this.http.get(this.construirURL(url));
  }

  protected construirPeticionPost(url: string, body: any): any {
    return this.http.post(this.construirURL(url), body);
  }

  protected construirPeticionPut(url: string, body: any): any {
    return this.http.put(this.construirURL(url), body);
  }

  protected construirPeticionDelete(url: string): any {
    return this.http.delete(this.construirURL(url));
  }
}
