package com.elevenst.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductRemoteServiceImpl implements ProductRemoteService {
    public static final String url = "http://product/products/";
    private final RestTemplate restTemplate;

    public ProductRemoteServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(commandKey = "productInfo", fallbackMethod = "getProductInfoFallback")
    @Override
    public String getProductInfo(String productId) {
        String productInfo =
                restTemplate.getForObject(
                        url +productId,
                        String.class);
        return productInfo;
    }

    public String getProductInfoFallback(String productId, Throwable t) {
        System.out.println("t = " + t);
        return "[ this product is sold out ]";
    }
}
