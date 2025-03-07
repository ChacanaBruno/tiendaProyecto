package com.proyecto.tienda.service.sale;

import com.proyecto.tienda.dto.ClientUpdateDTO;
import com.proyecto.tienda.dto.SaleUpdateDTO;
import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.model.Sale;

import java.util.List;

public interface ISaleService {
    public List<Sale> getSales();

    public void saveSale(Sale sale);

    public void deleteSaleById(Long id);

    public Sale findSaleById(Long id);

    public void editSale(Long id_original, SaleUpdateDTO saleUpdateDTO);

}
