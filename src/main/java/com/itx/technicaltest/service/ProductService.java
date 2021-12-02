package com.itx.technicaltest.service;

import com.itx.technicaltest.entity.Product;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@Service
public class ProductService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Log LOGGER;

    public String[] getSimilarProductsIds(String id)
    {
        //Prepare Request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        RequestEntity request;
        try {
            request = new RequestEntity(headers, HttpMethod.GET, new URI("http://localhost:3001/product/"+id+"/similarids"));
        }
        catch (URISyntaxException e)
        {
            LOGGER.error("URL: http://localhost:3001/product/{id}/similarids |" + e.getMessage());
            return null;
        }

        //Execute API Rest Request - Get Similar Products Ids
        ResponseEntity<String[]> response = restTemplate.exchange(request, String[].class);

        return response.getBody();
    }

    public Product getDetails(String id)
    {
        //Prepare Request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        RequestEntity request;
        try{
             request = new RequestEntity(headers, HttpMethod.GET, new URI("http://localhost:3001/product/"+id));
        }
        catch (URISyntaxException e)
        {
            LOGGER.error("URL: http://localhost:3001/product/ |" + e.getMessage());
            return null;
        }

        //Execute API Rest Request - Get Similar Products Ids
        ResponseEntity<Product> response = restTemplate.exchange(request, Product.class);

        return response.getBody();
    }
}
