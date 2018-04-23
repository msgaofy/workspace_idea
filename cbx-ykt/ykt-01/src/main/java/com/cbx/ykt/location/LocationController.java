package com.cbx.ykt.location;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @RequestMapping("/test")
    public void test(){
        System.out.println("hello");
    }

    //local提交
}
