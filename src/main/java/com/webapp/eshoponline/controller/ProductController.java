package com.webapp.eshoponline.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.eshoponline.model.Category;
import com.webapp.eshoponline.model.Product;
import com.webapp.eshoponline.service.ICategoryService;
import com.webapp.eshoponline.service.IProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ICategoryService catService;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/addproduct")
	public String getAddProduct(Model model) {
		model.addAttribute("category",catService.getAll()) ;
		return "AddProductForm";
	}
	
	@GetMapping("/addcategory")
	public String getAddCategory() {
		
		
		
		return "AddCategoryForm";
	}
	
	@PostMapping("/addcategory")
	public String postAddCategory(@ModelAttribute Category category) {
		
		catService.addCategory(category);
		
		return "AddCategoryForm";
	}
	
	@PostMapping("/addproduct")
	
	 public String postAddProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile image, @RequestParam("category") Long categoryId, Model model) {

        if (!image.isEmpty()) {
            String destinationDirectory = "C:\\Users\\Old MoNk\\Documents\\workspace-spring-tool-suite-4-4.18.0.RELEASE\\E-commerceShopping\\src\\main\\resources\\static/";

            // Retrieve the selected category from the database
            Category category = catService.getCategoryById(categoryId);

            if (category != null) {
                destinationDirectory += category.getName() + "/";

                try {
                	// Save the product details in the database
                	product.setImageName(image.getOriginalFilename());
                	productService.addProduct(product);

                	
                    // Save the product details in the database
                    productService.addProduct(product);
                    // Save the image file
   				Files.copy(image.getInputStream(), Path.of(destinationDirectory,image.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING);

//                    String originalFilename = image.getOriginalFilename();
//                    Path destinationPath = Path.of(destinationDirectory, originalFilename);
//                    Files.copy(image.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
   			        model.addAttribute("Clist",catService.getAll());
                    model.addAttribute("successmessage", "Upload success");
                    model.addAttribute("category",catService.getAll()) ;
                    return "AddProductForm";
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("error", "Upload failed: Error saving the image");
                }
                finally {
                    // Delete the temporary file
                    try {
                        image.getInputStream().close();
                        image.transferTo(new File(image.getOriginalFilename()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        model.addAttribute("error", "Failed to delete the temporary file");
                    }
                }
                
            } else {
                model.addAttribute("invalidmessage", "Upload failed: Invalid category");
            }
        } else {
            model.addAttribute("nofileselected", "Upload failed: No file selected");
        }
        model.addAttribute("Clist",catService.getAll()) ;
        model.addAttribute("category",catService.getAll()) ;
        return "AddProductForm";
    }
	
	@GetMapping("/laptops")
	public String getLaptops(Model model,@RequestParam("query") String query) {
	    // Retrieve the list of products from the database
	   // List<Product> products = productService.getProductByCategory(id);
	    
		String laptops = query;
         Category category = catService.getCategoryByName(laptops);
         
         Long id = category.getId();
	    // Add the products to the model
         List<Product> products = productService.getProductByCategory(id);
	    model.addAttribute("products", products);
	    
	    return "Laptops";
	}
	@GetMapping("/smartphones")
	public String getSmartphones(Model model,@RequestParam("query") String query) {
	    // Retrieve the list of products from the database
	   // List<Product> products = productService.getProductByCategory(id);
	    
		String smartphones = query;
         Category category = catService.getCategoryByName(smartphones);
         
         Long id = category.getId();
	    // Add the products to the model
         List<Product> products = productService.getProductByCategory(id);
	    model.addAttribute("products", products);
	    
	    return "Smartphones";
	}
	@GetMapping("/cameras")
	public String getCameras(Model model,@RequestParam("query") String query) {
	    // Retrieve the list of products from the database
	   // List<Product> products = productService.getProductByCategory(id);
	    
		String cameras = query;
         Category category = catService.getCategoryByName(cameras);
         
         Long id = category.getId();
	    // Add the products to the model
         List<Product> products = productService.getProductByCategory(id);
	    model.addAttribute("products", products);
	    
	    return "Cameras";
	}
	@GetMapping("/accessories")
	public String getAccessories(Model model,@RequestParam("query") String query) {
	    // Retrieve the list of products from the database
	   // List<Product> products = productService.getProductByCategory(id);
	    
		String accessories = query;
         Category category = catService.getCategoryByName(accessories);
         
         Long id = category.getId();
	    // Add the products to the model
         List<Product> products = productService.getProductByCategory(id);
	    model.addAttribute("products", products);
	    
	    return "Accessories";
	}
	
	@GetMapping("/productlist")
	public String getProductList(Model model) {
		
		 List<Product> productList =productService.getAllProducts();
	      for (Product products : productList) {
		        Product product = productService.getByName(products.getName());

		        String category;
		        if (product.getCategory() == 1) {
		            category = "laptops";
		           
		            
		        } else if (product.getCategory() == 2) {
		            category = "smartphones";
		           
		        } else if (product.getCategory() == 3) {
		            category = "cameras";
		          
		        } else if (product.getCategory() == 4) {
		            category = "accessories";
		           
		        } else {
		            category = "unknown";
		        }
		        
		      
		        products.setImageName("/"+category+"/"+products.getImageName());
		       
		 }
			model.addAttribute("product",productList);
		
		
		
		return "ProductList";
	}
	
	@GetMapping("/deleteproduct/{id}")
	public String getDeleteProduct(@PathVariable("id") Long id,HttpServletRequest request) {
		
		productService.deleteById(id);
		
		 String referer = request.getHeader("referer");
		 if (referer != null && !referer.isEmpty()) {
		        return "redirect:" + referer;
		    } else {
		        return "redirect:/"; // Fallback to a default page if referer is empty or null
		    }
	}


}
