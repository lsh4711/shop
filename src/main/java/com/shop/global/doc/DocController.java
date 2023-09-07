package com.shop.global.doc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class DocController {
    @GetMapping
    public String getDoc() {
        return "redirect:/docs/swagger-ui/index.html";
    }
}
