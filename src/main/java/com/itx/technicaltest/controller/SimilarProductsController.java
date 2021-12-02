package com.itx.technicaltest.controller;

import com.itx.technicaltest.entity.Product;
import com.itx.technicaltest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SimilarProductsController {

    @Autowired
    ProductService productService;

    @GetMapping(path="/product/{productId}/similar")
    public ArrayList<Product> similarProducts(@PathVariable("productId") String productId)
    {
        //Get similars ids
        String[] similarIds = productService.getSimilarProductsIds(productId);

        //Prepare response
        ArrayList<Product> similarProducts = new ArrayList<Product>();
        //Search detail of each similar product
        for(String id: similarIds)
        {
            similarProducts.add(productService.getDetails(id));
        }

        return similarProducts;
    }
}
