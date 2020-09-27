import { Injectable } from '@angular/core';
import { Category } from '../entity/category.entity';
import { RestAPIService } from './rest-api.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private restService: RestAPIService) {
  }

  async findAll() {
    const res = await this.restService.get('category', 'parents')
      .toPromise();
    return res as Category[];
  }

}
