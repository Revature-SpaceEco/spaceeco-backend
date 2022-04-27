package com.revature.spaceecobackend.dto;


import com.revature.spaceecobackend.model.Categories;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {

    private int productId;
    private String productName;
    private String productDescription;
    private int productCost;
    private Categories categories;
    private String productImage;
    private SellerDto sellerInfo;

}
