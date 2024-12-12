import { Component, ViewChild } from '@angular/core';

import { CategoryService } from '../../Services/category.service';
import { ProductsService } from '../../Services/products.service';

import { Products } from '../../Model/Products';
import { EnvService } from '../../../../env';
@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  @ViewChild('productForm') productForm: any;

  products: any = [];

  isShowAddProductForm: boolean = false;

  env = new EnvService();
  imageUrl: string = this.env.API_URL + '/files/';

  categories: any = [];
  constructor(
    private categoryService: CategoryService,
    private productService: ProductsService
  ) {

    this.getAllCategories();
    this.getAllProducts();
  }

  imageFile: any = null;

  onImageFileSelected(event: any) {

    let tempFile = {
      name: event.target.files[0].name,
      size: event.target.files[0].size, // size in bytes
      type: event.target.files[0].type,
      lastModified: event.target.files[0].lastModified,
      fileExtension: event.target.files[0].name.split(".")[1]

    }




    this.imageFile = event.target.files[0];

    const reader = new FileReader();
    reader.readAsDataURL(this.imageFile);
    reader.onload = () => {
      console.log(reader.result);

      this.imageFile = reader.result;
      this.imageFile = this.imageFile.split(",")[1];

      this.imageFile = {
        name: tempFile.name,
        size: tempFile.size,
        type: tempFile.type,
        lastModified: tempFile.lastModified,
        fileExtension: tempFile.fileExtension,
        file: this.imageFile
      }
    };

    console.log(this.imageFile);


  }

  modelFile: any = null;
  onModelFileSelected(event: any) {

    let tempFile = {
      name: event.target.files[0].name,
      size: event.target.files[0].size,
      type: event.target.files[0].type,
      lastModified: event.target.files[0].lastModified,
      fileExtension: event.target.files[0].name.split(".")[1]

    }

    this.modelFile = event.target.files[0];

    const reader = new FileReader();
    reader.readAsDataURL(this.modelFile);
    reader.onload = () => {
      console.log(reader.result);

      this.modelFile = reader.result;
      this.modelFile = this.modelFile.split(",")[1];

      this.modelFile = {
        name: tempFile.name,
        size: tempFile.size,
        type: tempFile.type,
        lastModified: tempFile.lastModified,
        fileExtension: tempFile.fileExtension,
        file: this.modelFile
      }
    };

    console.log(this.modelFile);
  }


  bufferFile: any = null;
  onBufferFileSelected(event: any) {
      
      let tempFile = {
        name: event.target.files[0].name,
        size: event.target.files[0].size,
        type: event.target.files[0].type,
        lastModified: event.target.files[0].lastModified,
        fileExtension: event.target.files[0].name.split(".")[1]
  
      }
  
      this.bufferFile = event.target.files[0];
  
      const reader = new FileReader();
      reader.readAsDataURL(this.bufferFile);
      reader.onload = () => {
        console.log(reader.result);
  
        this.bufferFile = reader.result;
        this.bufferFile = this.bufferFile.split(",")[1];
  
        this.bufferFile = {
          name: tempFile.name,
          size: tempFile.size,
          type: tempFile.type,
          lastModified: tempFile.lastModified,
          fileExtension: tempFile.fileExtension,
          file: this.bufferFile
        }
      };
  
      console.log(this.bufferFile);


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

  editProduct(product: any) {

  }

  deleteProduct(product: any) {

    this.productService.deleteProduct(product).subscribe({
      next: (data: any) => {
        console.log(data);
        if (data[0].status) {
          alert(data[0].message);
          this.getAllProducts();
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

  getAllProducts() {

    this.productService.getAllProduct().subscribe({
      next: (data: any) => {
        console.log(data);
        this.products = data;
      },
      error: (err) => {
        console.log(err);

      },
      complete: () => {
        console.log("Completed");
      }

    });

  }
  addProduct(pForm: any) {

    let product: Products = {
      id: 0,
      name: pForm.value.name,
      description: pForm.value.description,
      price: pForm.value.price,
      productCategoryID: pForm.value.productCategoryID,
      image: this.imageFile,
      model: this.modelFile,
      modelBufferFile: this.bufferFile

    }

    console.log(product);

    this.productService.createProduct(product).subscribe({
      next: (data: any) => {
        console.log(data);
        if (data[0].status) {
          alert(data[0].message);
          this.productForm.reset();
          this.isShowAddProductForm = false;
          this.getAllProducts();
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
