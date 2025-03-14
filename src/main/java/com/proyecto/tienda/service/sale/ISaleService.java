package com.proyecto.tienda.service.sale;


import com.proyecto.tienda.dto.SaleUpdateDTO;
import com.proyecto.tienda.model.Sale;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISaleService {
    public List<Sale> getSales();

    public ResponseEntity<Sale> saveSale(SaleUpdateDTO save);

    public void deleteSaleById(Long id);

    public Sale findSaleById(Long id);

    public void editSale(Long id_original, SaleUpdateDTO saleUpdateDTO);

}
