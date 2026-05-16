package com.smart_inventory.smart_inventory.controller;

import com.smart_inventory.smart_inventory.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
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

        // Total products
        long totalProducts = productRepository.count();

        // Total inventory value
        Double inventoryValue =
                productRepository.getTotalInventoryValue();

        // Low stock products
        Long lowStockCount =
                productRepository.getLowStockCount();

        // Total categories
        Long totalCategories =
                productRepository.getTotalCategories();

        // Top 5 products
        var topProducts =
                productRepository.findTopProducts(PageRequest.of(0, 5));

        // Send data to dashboard
        model.addAttribute("totalProducts", totalProducts);

        model.addAttribute("inventoryValue",
                inventoryValue != null ? inventoryValue : 0);

        model.addAttribute("lowStockCount",
                lowStockCount);

        model.addAttribute("totalCategories",
                totalCategories);

        model.addAttribute("topProducts",
                topProducts);

        return "dashboard";
    }
}
