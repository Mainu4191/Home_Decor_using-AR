package com.example.arproject1.Controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.arproject1.Entities.Orders;
import com.example.arproject1.Entities.ProductCategory;
import com.example.arproject1.Entities.Products;
import com.example.arproject1.Entities.UsersEntity;
import com.example.arproject1.Model.Dashboard;
import com.example.arproject1.Model.OrdersAdminDetails;
import com.example.arproject1.Model.ProductsRequestModel;
import com.example.arproject1.Services.DashboardServices;
import com.example.arproject1.Services.FileServices;
import com.example.arproject1.Services.OrdersServices;
import com.example.arproject1.Services.ProductCategoryService;
import com.example.arproject1.Services.ProductsServices;
import com.example.arproject1.Services.UsersServices;
import com.example.arproject1.utils.DecoderUtil;
import com.example.arproject1.utils.Response;

@SuppressWarnings("unused")
record fileContent(String filename, String fileContent) {
}

@RestController
@RequestMapping("/api")
public class Api {

    private final ProductCategoryService productCategoryService;
    private final ProductsServices productsServices;
    private final FileServices fileServices;
    private final UsersServices usersServices;
    private final OrdersServices ordersServices;
    private final DashboardServices dashboardServices;

    public Api(
            ProductCategoryService productCategoryService,
            ProductsServices productsServices,
            FileServices fileServices,
            UsersServices usersServices,
            OrdersServices ordersServices,
            DashboardServices dashboardServices) {
        this.productCategoryService = productCategoryService;
        this.productsServices = productsServices;
        this.fileServices = fileServices;
        this.usersServices = usersServices;
        this.ordersServices = ordersServices;
        this.dashboardServices = dashboardServices;
    }

    @GetMapping("/hello")
    public List<String> hello() {
        return List.of("Hello", "World");
    }

    @PostMapping("/test")
    public String test(@RequestBody fileContent fileContent) {
        System.out.println(fileContent.filename());
        System.out.println(fileContent.fileContent());
        return "test";

    }


    @PostMapping("/product-category/add")
    public List<Response> createProductCategory(@RequestBody ProductCategory productCategory) {

        String name = productCategory.getName();
        String description = productCategory.getDescription();

        boolean isCreated = productCategoryService.createProductCategory(name, description);
        if (isCreated) {

            Response response = new Response("Product Category Created Successfully", true);

            return List.of(response);
        } else {

            Response response = new Response("Failed to create Product Category", false);

            return List.of(response);
        }

    }


    @PostMapping("/product-category/update")
    public List<Response> updateProductCategory(@RequestBody ProductCategory productCategory) {

        long id = productCategory.getId();
        String name = productCategory.getName();
        String description = productCategory.getDescription();

        boolean isUpdated = productCategoryService.updateProductCategory(id, name, description);
        if (isUpdated) {

            Response response = new Response("Product Category Updated Successfully", true);

            return List.of(response);
        } else {

            Response response = new Response("Failed to update Product Category", false);
            return List.of(response);
        }

    }


    @PostMapping("/product-category/delete")
    public List<Response> deleteProductCategory(@RequestBody ProductCategory productCategory) {

        long id = productCategory.getId();

        boolean isDeleted = productCategoryService.deleteProductCategory(id);
        if (isDeleted) {

            Response response = new Response("Product Category Deleted Successfully", true);

            return List.of(response);
        } else {
            Response response = new Response("Failed to delete Product Category", false);
            return List.of(response);
        }

    }


    @GetMapping("/product-category/get-all")
    public List<ProductCategory> getAllProductCategory() {

        System.out.println("Get All Product Category");

        List<ProductCategory> productCategories = productCategoryService.getAllProductCategory();
        return productCategories;

    }


    @PostMapping("/product-category/get-by-id")
    public ProductCategory getProductCategoryById(@RequestBody ProductCategory productCategory) {

        long id = productCategory.getId();

        ProductCategory productCategoryById = productCategoryService.getProductCategoryById(id);
        return productCategoryById;

    }

    @PostMapping("/product/add")
    public List<Response> createProduct(@RequestBody ProductsRequestModel products) {

        System.out.println("-------------------");

        String date = System.currentTimeMillis() + "";

        String folderName = date.toString();

        boolean isFolderCreated = fileServices.createFolder(folderName);

        if (isFolderCreated) {
            System.out.println("Folder Created Successfully");
        } else {
            System.out.println("Failed to create Folder");
        }

        String imageFile = products.image().getFile();
        byte[] imageFileDecoded = DecoderUtil.Base64Decoder(imageFile);

        String imageFileName = date.toString() + products.image().getName();
        boolean isImageCreated = fileServices.createFile(imageFileName, imageFileDecoded, folderName);
        if (isImageCreated) {
            System.out.println("Image Created Successfully");
        } else {
            System.out.println("Failed to create Image");
        }

        String modelFile = products.model().getFile();
        byte[] modelFileDecoded = DecoderUtil.Base64Decoder(modelFile);
        String modelFileName = date.toString() + products.model().getName();
        boolean isModelCreated = fileServices.createFile(modelFileName, modelFileDecoded, folderName);
        if (isModelCreated) {
            System.out.println("Model Created Successfully");
        } else {
            System.out.println("Failed to create Model");
        }

        String modelBufferFile = products.modelBufferFile().getFile();
        byte[] modelBufferFileDecoded = DecoderUtil.Base64Decoder(modelBufferFile);
        String modelBufferFileName = products.modelBufferFile().getName();
        boolean isModelBufferCreated = fileServices.createFile(modelBufferFileName, modelBufferFileDecoded, folderName);
        if (isModelBufferCreated) {
            System.out.println("Model Buffer Created Successfully");
        } else {
            System.out.println("Failed to create Model Buffer");
        }

        System.out.println("-------------------");


        boolean isCreated = productsServices.createProduct(
                products.name(),
                products.description(),
                imageFileName,
                products.productCategoryID(),
                modelFileName,
                modelBufferFileName,
                folderName,
                products.price());

        if (isCreated) {
            return List.of(new Response("Product Created Successfully", true));
        } else {
            return List.of(new Response("Failed to create Product", false));
        }

    }


    @PostMapping("/product/update")
    public String updateProduct(@RequestBody Products products) {

        long id = products.getId();
        String name = products.getName();
        String description = products.getDescription();
        String image = products.getImage();
        String productCategoryID = products.getproductCategoryID();
        String model = products.getModel();
        String price = products.getPrice();

        boolean isUpdated = productsServices.updateProduct(id, name, description, image, productCategoryID, model,
                price);
        if (isUpdated) {
            return "Product Updated Successfully";
        } else {
            return "Failed to update Product";
        }

    }


    @PostMapping("/product/delete")
    public List<Response> deleteProduct(@RequestBody Products products) {

        long id = products.getId();

        boolean isDeleted = productsServices.deleteProduct(id);
        if (isDeleted) {

            Response response = new Response("Product Deleted Successfully", true);
            return List.of(response);
        } else {
            Response response = new Response("Failed to delete Product", false);
            return List.of(response);
        }

    }


    @GetMapping("/product/get-all")
    public List<Products> getAllProduct() {

        System.out.println("Get All Product");

        List<Products> products = productsServices.getAllProduct();
        return products;

    }


    @PostMapping("/product/get-by-id")
    public List<Products> getProductById(@RequestBody Products products) {

        long id = products.getId();

        Products productById = productsServices.getProductById(id);
        return List.of(productById);

    }


    @GetMapping("/users/get-all")
    public List<UsersEntity> getAllUser() {

        System.out.println("Get All User");

        List<UsersEntity> users = usersServices.getAllUser();
        return users;

    }

    @PostMapping("/users/delete")
    public List<Response> deleteUser(@RequestBody UsersEntity usersEntity) {

        long id = usersEntity.getId();

        boolean isDeleted = usersServices.deleteUser(id);
        if (isDeleted) {

            Response response = new Response("User Deleted Successfully", true);
            return List.of(response);
        } else {
            Response response = new Response("Failed to delete User", false);
            return List.of(response);
        }

    }

 
    @GetMapping("/orders/get-all")
    public List<OrdersAdminDetails> getAllOrders() {

        List<OrdersAdminDetails> ordersList = ordersServices.getAllOrdersAdmin();
        return ordersList;

    }

    @GetMapping("/orders/get-by-id")
    public OrdersAdminDetails getOrderDetailsById(@Param("id") long id) {

        OrdersAdminDetails orderDetails = ordersServices.getOrderAdminById(id);
        return orderDetails;
    }


    @PostMapping("/orders/update-status")
    public Response updateOrderStatus(@RequestBody OrdersAdminDetails ordersAdminDetails) {

        long id = ordersAdminDetails.getOrder_id();

        String orderStatus = ordersAdminDetails.getStatus();

        System.out.println("Update Order Status");
        System.out.println(id);
        System.out.println(orderStatus);

        boolean isUpdated = ordersServices.updateOrderStatus(id, orderStatus);
        if (isUpdated) {

            Response response = new Response("Order Status Updated Successfully", true);
            return response;
        } else {
            Response response = new Response("Failed to update Order Status", false);
            return response;
        }
       

    }

   
    @GetMapping("/dashboard/get-data")
    public Dashboard getDashboardData() {

        Dashboard dashboard = dashboardServices.getDashboardData();
        return dashboard;
    }

}
