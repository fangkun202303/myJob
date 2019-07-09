package com.moduls.myxxljob.controller;

import com.moduls.myxxljob.service.MyServiceOfXxl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @program: my-elastic-job
 * @author: fangkun
 * @version： 1.0.0
 * @create: 2019-07-05
 */
@RestController
public class IndexController {

    @Autowired
    private MyServiceOfXxl myServiceOfXxl;

    @GetMapping("runMyService")
    public String runService(){
        return myServiceOfXxl.runMyService("");
    }
}
