package com.revature.spaceecobackend.dto;


import com.revature.spaceecobackend.model.Categories;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {

    private int id;
    private String name;
    private String description;
    private int cost;
    private String categoryName;
    private String image;
    private SellerDto seller;

}
