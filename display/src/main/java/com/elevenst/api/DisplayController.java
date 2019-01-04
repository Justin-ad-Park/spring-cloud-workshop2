package com.elevenst.api;

import com.elevenst.service.FeignProductRemoteService;
import com.elevenst.service.ProductRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/displays")
public class DisplayController {

    private final ProductRemoteService productRemoteService;
    private final FeignProductRemoteService feignProductRemoteService;

//    @Autowired
//    private FeignProductRemoteService feignProductRemoteService;

    public DisplayController(ProductRemoteService productRemoteService, FeignProductRemoteService feignProductRemoteService) {
        this.productRemoteService = productRemoteService;
        this.feignProductRemoteService = feignProductRemoteService;
    }

    @GetMapping(path = "/{displayId}")
    public String getDisplayDetail(@PathVariable String displayId) {
        String productInfo = getProductInfo(displayId);
        return String.format("[display id = %s at %s %s ]", displayId, System.currentTimeMillis(), productInfo);
    }

    private String getProductInfo(String productId) {
        //return productRemoteService.getProductInfo(productId);
        return feignProductRemoteService.getProductInfo(productId);
    }
}