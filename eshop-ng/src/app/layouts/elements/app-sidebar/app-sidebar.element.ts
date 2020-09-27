import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/entity/category.entity';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './app-sidebar.element.html',
  styleUrls: ['./app-sidebar.element.css']
})
export class AppSidebarElement implements OnInit {
  categories: Category[];
  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.categoryService.findAll()
      .then(res => {
        this.categories = res;
        console.log(this.categories);
      },
        error => {
          alert(error);
        }
      );
  }

}
