import { Component, ViewChild } from '@angular/core';
import { ProductCategory } from '../../Model/Product-Category';
import { CategoryService } from '../../Services/category.service';
@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent {




  @ViewChild('categoryForm') categoryForm: any;

  isShowAddCategoryForm: boolean = false;
  categories: any = [];


  constructor(private categoryService: CategoryService) {

    this.getAllCategories();

  }


  getAllCategories() {

    this.categoryService.getAllProductCategory().subscribe({
      next: (data: any) => {
        console.log(data);
        this.categories = data;
      },
      error: (err) => {
        console.log(err);

      },
      complete: () => {
        console.log("Completed");
      }

    });

  }


  addCategory() {
    console.log(this.categoryForm.value);

    let category = new ProductCategory(
      this.categoryForm.value.id,
      this.categoryForm.value.name,
      this.categoryForm.value.description
    );

    this.categoryService.addProductCategory(category).subscribe({
      next: (data: any) => {
        console.log(data);

        if (data[0].status) {
          alert(data[0].message);
          this.isShowAddCategoryForm = false;
          this.categoryForm.reset();
          this.getAllCategories();
        }


      },
      error: (err) => {
        console.log(err);
      },
      complete: () => {
        console.log("Completed");
      }

    });
  }

  editCategory(category: any) {

    let categoryName: any = prompt("Please enter category name", category.name);
    let categoryDescription: any = prompt("Please enter category description", category.description);

    if (categoryName == null || categoryName == "") {
      alert("Category name is required");
      return;
    }

    let categoryObj = new ProductCategory(
      category.id,
      categoryName,
      categoryDescription
    );

    this.categoryService.updateProductCategory(categoryObj).subscribe({
      next: (data: any) => {
        console.log(data);

        if (data[0].status) {
          alert(data[0].message);
          this.getAllCategories();
        }

      },
      error: (err) => {
        console.log(err);
      },
      complete: () => {
        console.log("Completed");
      }

    });

  }

  deleteCategory(category: any) {

    let isDelete = confirm("Are you sure you want to delete this category?");

    if (isDelete) {
      this.categoryService.deleteProductCategory(category).subscribe({
        next: (data: any) => {
          console.log(data);
          if (data[0].status) {
            alert(data[0].message);
            this.getAllCategories();
          } else {
            alert(data[0].message);
          }


        },
        error: (err) => {
          console.log(err);
        },
        complete: () => {
          console.log("Completed");
        }

      });
    }

  }

}
