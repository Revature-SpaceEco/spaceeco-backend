package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.ProductRepository;
import com.revature.spaceecobackend.dto.ProductDto;
import com.revature.spaceecobackend.dto.SellerDto;
import com.revature.spaceecobackend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
      pdto.setProductId(product.getProductId());
      pdto.setProductName(product.getProductName());
      pdto.setProductDescription(product.getProductDescription());
      pdto.setProductCost(product.getProductCost());
      pdto.setCategories(product.getCategories());
      pdto.setProductImage(product.getProductImage());

      SellerDto sdto = new SellerDto();
      sdto.setId(product.getProductSellerId().getId());
      sdto.setUsername(product.getProductSellerId().getUsername());
      sdto.setEmail(product.getProductSellerId().getEmail());
      sdto.setFirstName(product.getProductSellerId().getFirstName());
      sdto.setActive(product.getProductSellerId().isActive());

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