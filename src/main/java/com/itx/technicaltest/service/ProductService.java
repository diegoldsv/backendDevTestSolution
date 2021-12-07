package com.itx.technicaltest.service;

import com.itx.technicaltest.entity.Product;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    private final RestTemplate restTemplate;
    private final Log LOGGER;

    private final String mocksBaseUrl="http://localhost:3001/product";

    public ProductService(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplate = restTemplateBuilder.build();
        this.LOGGER = LogFactory.getLog(ProductService.class);
    }

    public List<Product> getSimilarProducts(Integer id)
    {
        LOGGER.debug(String.format("Getting similar product ids for product %d...", id));
        List<Integer> similarProductsIds = this.getSimilarProductsIds(id);
        LOGGER.debug(String.format("Similar ids found: %d", similarProductsIds.size()));

        //Search detail of each similar product
        ArrayList<Product> similarProducts = new ArrayList<>();
        for(Integer similarProductId: similarProductsIds)
        {
            LOGGER.debug(String.format("Getting details for product %d...", similarProductId));
            Product productDetail = this.getDetails(similarProductId);
            if(productDetail != null)
            {
                similarProducts.add(productDetail);
                LOGGER.debug("Product found!");
            }
            else
            {
                LOGGER.debug("Product not found! It is not included in the list of similar products");
            }
        }

        return similarProducts;
    }

    public List<Integer> getSimilarProductsIds(Integer id)
    {
        //BUILD REQUEST: Get Similar Products Ids
        RequestEntity<Void> request;
        //Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //Path
        String mockSimilarIdsPath = "/{id}/similarids";
        String path = this.mocksBaseUrl + mockSimilarIdsPath.replace("{id}", id.toString());
        try {
            request = new RequestEntity<>(headers, HttpMethod.GET, new URI(path));
        }
        catch (URISyntaxException e)
        {
            LOGGER.error(String.format("Request: %s, Exception: %s", path, e.getMessage()));
            return Collections.emptyList();
        }

        //EXECUTE REQUEST
        try{
            LOGGER.debug(String.format("Executing request: %s...", path));
            ResponseEntity<List<Integer>> response = restTemplate.exchange(request, new ParameterizedTypeReference<>(){});
            LOGGER.debug(String.format("[STATUS] %s", response.getStatusCode()));
            LOGGER.debug(String.format("[RESPONSE] %s", response.getBody()));
            return response.getBody();
        }
        catch (RestClientResponseException e)
        {
            LOGGER.error(String.format("Request: %s, Exception: %s", path, e.getMessage()));
            return Collections.emptyList();
        }
    }

    public Product getDetails(Integer id)
    {
        //PREPARE REQUEST: Get Product details
        RequestEntity<Void> request;
        //Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //Path
        String mockProductDetailsPath = "/{id}";
        String path = this.mocksBaseUrl + mockProductDetailsPath.replace("{id}", id.toString());
        try{
             request = new RequestEntity<>(headers, HttpMethod.GET, new URI(path));
        }
        catch (URISyntaxException e)
        {
            LOGGER.error(String.format("Request: %s, Exception: %s", path, e.getMessage()));
            return null;
        }

        //EXECUTE REQUEST
        try{
            LOGGER.debug(String.format("Executing request: %s...", path));
            ResponseEntity<Product> response = restTemplate.exchange(request, Product.class);
            LOGGER.debug(String.format("[STATUS] %s", response.getStatusCode()));
            LOGGER.debug(String.format("[RESPONSE] %s", response.getBody()));
            return response.getBody();
        }
        catch (RestClientResponseException e)
        {
            LOGGER.error(String.format("Request: %s, Exception: %s", path, e.getMessage()));
            return null;
        }
    }
}
