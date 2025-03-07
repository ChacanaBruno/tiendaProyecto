package com.proyecto.tienda.service.sale;

import com.proyecto.tienda.dto.SaleUpdateDTO;
import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.model.Sale;
import com.proyecto.tienda.repository.ISaleRepository;

import java.util.List;

public class SaleService implements ISaleService{

    private ISaleRepository saleRepository;

    public SaleService(ISaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    @Override
    public void saveSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public void deleteSaleById(Long id) {
        saleRepository.deleteById(id);
    }

    @Override
    public Sale findSaleById(Long id) {

        Sale sale = saleRepository.findById(id).orElse(null);

        return sale;
    }

    @Override
    public void editSale(Long id_original, SaleUpdateDTO saleUpdateDTO) {

        Sale saleEdit = this.findSaleById(id_original);


    }
}
