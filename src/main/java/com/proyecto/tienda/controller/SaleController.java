package com.proyecto.tienda.controller;
import com.proyecto.tienda.dto.SaleUpdateDTO;
import com.proyecto.tienda.model.Sale;
import com.proyecto.tienda.service.sale.ISaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaleController {
    ISaleService saleService;

    public SaleController(ISaleService saleService) {this.saleService = saleService;}

    @GetMapping("/sales/fetch")
    public List<Sale> getSales() {

        return saleService.getSales();
    }

    @PostMapping("/sales/create")
    public String createProduct(@RequestBody Sale sale) {

        saleService.saveSale(sale);

        return "Successfully created sale";
    }

    @DeleteMapping("/sales/delete/{id}")
    public String deleteSaleById(@PathVariable Long id) {
        saleService.deleteSaleById(id);
        return "Successfully deleted sale";
    }

    @GetMapping("/sales/find/{id}")
    public Sale findSaleById(@PathVariable Long id) {
        return saleService.findSaleById(id);
    }

    @PutMapping("sales/edit/{code_sale}")
    public Sale editSale(@PathVariable Long code_sale,
                            @RequestBody SaleUpdateDTO saleUpdateDTO) {

        saleService.editSale(code_sale, saleUpdateDTO);

        return saleService.findSaleById(code_sale);
    }
}
