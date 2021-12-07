package com.itx.technicaltest.controller;

import com.itx.technicaltest.entity.Product;
import com.itx.technicaltest.service.ProductService;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SimilarProductsController {

    private final ProductService productService;
    private final Log LOGGER;

    public SimilarProductsController(ProductService productService)
    {
        this.productService = productService;
        this.LOGGER = LogFactory.getLog(SimilarProductsController.class);
    }

    @GetMapping(path="/product/{productId}/similar")
    public List<Product> similarProducts(@PathVariable("productId") Integer productId)
    {
        LOGGER.info(String.format("Processing request: Get Similar Products, for product (id): %d", productId));
        List<Product> similarProducts = productService.getSimilarProducts(productId);
        LOGGER.info(String.format("Similar products found: %d", similarProducts.size()));

        return similarProducts;
    }
}
