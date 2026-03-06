package com.smart_inventory.smart_inventory.controller;

import com.smart_inventory.smart_inventory.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ProductRepository productRepository;

    public DashboardController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        long totalProducts = productRepository.count();

        model.addAttribute("totalProducts", totalProducts);

        return "dashboard";
    }
}
