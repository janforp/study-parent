package com.janita.sentinel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author zhucj
 * @since 20210729
 */
@RestController(value = "/api/sentinel/test")
public class TestController {

    @RequestMapping(value = "/doTest", method = RequestMethod.GET)
    public String test(@RequestParam(required = false) String id) {
        return "收到消息，" + id;
    }
}
