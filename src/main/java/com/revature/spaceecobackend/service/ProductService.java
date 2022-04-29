package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.ProductRepository;
import com.revature.spaceecobackend.dto.ProductDto;
import com.revature.spaceecobackend.dto.SellerDto;
import com.revature.spaceecobackend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class ProductService implements ProductServiceInterface {

  @Autowired
  ProductRepository productRepo;


  public ProductDto convertProductToDto(Product product) {

      ProductDto pdto = new ProductDto();
      pdto.setId(product.getId());
      pdto.setName(product.getName());
      pdto.setDescription(product.getDescription());
      pdto.setCost(product.getCost());
      pdto.setCategory(product.getCategories());
      pdto.setImage(product.getImage());

      SellerDto sdto = new SellerDto();
      sdto.setId(product.getSeller().getId());
      sdto.setUsername(product.getSeller().getUsername());
      sdto.setEmail(product.getSeller().getEmail());
      sdto.setFirstName(product.getSeller().getFirstName());
      sdto.setActive(product.getSeller().isActive());

      pdto.setSellerInfo(sdto);

      return pdto;
  }

    public List<ProductDto> findAllProducts() {

      List<ProductDto> productDtos = new ArrayList<>();
      Product product = new Product();

        List<Product> products = new ArrayList<>();
        products = productRepo.findAll();
        for (Product p : products) {
            productDtos.add(convertProductToDto(p));

        }


        return productDtos;
    }

    public  ProductDto getProductsById(int productId) {

      Product product = new Product();

      Optional<Product> optional = productRepo.findById(productId);

      ProductDto pdto = new ProductDto();
      pdto = convertProductToDto(optional.orElse(null));

      return  pdto;
    }
}