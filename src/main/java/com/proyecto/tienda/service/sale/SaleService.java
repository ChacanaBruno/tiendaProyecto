package com.proyecto.tienda.service.sale;

import com.proyecto.tienda.dto.SaleUpdateDTO;
import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.model.Product;
import com.proyecto.tienda.model.Sale;
import com.proyecto.tienda.repository.IClientRepository;
import com.proyecto.tienda.repository.IProductRepository;
import com.proyecto.tienda.repository.ISaleRepository;
import com.proyecto.tienda.service.exceptions.TotalAmountErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements ISaleService{

    private ISaleRepository saleRepository;

    private IClientRepository clientRepository;

    private IProductRepository productRepository;

    public SaleService(ISaleRepository saleRepository, IClientRepository clientRepository, IProductRepository productRepository) {

        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    @Override
    public ResponseEntity<Sale> saveSale(Sale sale) {

        List<Product> validProducts = new ArrayList<>();

        for (Product product : sale.getListProducts()) {
            Product dbProduct = productRepository.findById(product.getCode_product())
                    .orElseThrow(() -> new IllegalArgumentException("Product no found: " + product.getCode_product()));

            if (dbProduct.getQuantity_available() <= 0) {
                throw new IllegalStateException("The product " + dbProduct.getName() + " no stock available.");
            }

            // Restar stock
            dbProduct.setQuantity_available(dbProduct.getQuantity_available() - 1);
            productRepository.save(dbProduct);
            validProducts.add(dbProduct);
        }




        Optional<Client> clientOptional = clientRepository.findById(sale.getClient().getId_client());

        if (!clientOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if(!sale.verifyTotalAmount()) {
             throw new TotalAmountErrorException();
        }

        sale.setClient(clientOptional.get());

        Sale saleSaved = saleRepository.save(sale);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(saleSaved.getCode_sale()).toUri();
        return ResponseEntity.created(location).body(saleSaved);
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

        saleEdit.updateFromDTO(saleUpdateDTO);
    }

}
