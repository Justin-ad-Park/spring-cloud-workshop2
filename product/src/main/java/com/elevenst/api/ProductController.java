package com.elevenst.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @GetMapping(path="/{productId}")
    public String getProductInfo(@PathVariable String productId) {

        long times = System.currentTimeMillis();
        System.out.println("Times = " + times);

        /* for circuit breaker */
//        try {
//            if(times % 2 == 1) {
//                Thread.sleep(2000);
//                throw new RuntimeException();
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String port = System.getProperty("server.port");

        return String.format("[Product ID = %s at %d, port = %s]", productId, System.currentTimeMillis(), port);
    }
}
