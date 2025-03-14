package com.proyecto.tienda.service.sale;

import com.proyecto.tienda.controller.Utils.SaleUtils;
import com.proyecto.tienda.dto.SaleUpdateDTO;
import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.model.Product;
import com.proyecto.tienda.model.Sale;
import com.proyecto.tienda.repository.IClientRepository;
import com.proyecto.tienda.repository.IProductRepository;
import com.proyecto.tienda.repository.ISaleRepository;
import com.proyecto.tienda.repository.exception.ClientNotFoundException;
import com.proyecto.tienda.service.client.ClientService;
import com.proyecto.tienda.service.exceptions.TotalAmountErrorException;
import com.proyecto.tienda.service.product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements ISaleService{

    private final ClientService clientService;
    private ISaleRepository saleRepository;

    private IClientRepository clientRepository;

    private IProductRepository productRepository;

    private ProductService productService;

    private SaleUtils saleUtils;

    public SaleService(ISaleRepository saleRepository,
                       IClientRepository clientRepository,
                       IProductRepository productRepository,
                       ProductService  productService, SaleUtils saleUtils, ClientService clientService) {

        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.saleUtils = saleUtils;
        this.clientService = clientService;
    }

    @Override
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    /*@Override
    @Transactional
    public ResponseEntity<Sale> saveSale(SaleUpdateDTO saleDTO) {

        //List<Product> validProducts = new ArrayList<>();

        productService.verifyProducts(saleDTO.getListProducts());

        clientService.verifyClient(saleDTO.getClient());


        Sale sale = new Sale(saleDTO.getDate_sale(), saleDTO.getTotal_amount(), saleDTO.getClient());

        Sale saleSaved = saleRepository.save(sale);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(saleSaved.getCode_sale()).toUri();
        return ResponseEntity.created(location).body(saleSaved);
    }*/
    @Override
    @Transactional
    public ResponseEntity<Sale> saveSale(SaleUpdateDTO saleDTO) {
        try {
            // Verificar cliente
            clientService.verifyClient(saleDTO.getClient());

            // Verificar productos sin modificar stock aún
            List<Product> validProducts = productService.verifyProducts(saleDTO.getListProducts());

            // Crear y guardar la venta
            Sale sale = new Sale(saleDTO.getDate_sale(), saleDTO.getTotal_amount(),validProducts, saleDTO.getClient());
            Sale saleSaved = saleRepository.save(sale);

            // Ahora sí, actualizar el stock
            productService.updateStockAfterSale(validProducts);

            // Construir respuesta con la URI de la venta creada
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saleSaved.getCode_sale())
                    .toUri();

            return ResponseEntity.created(location).body(saleSaved);

        } catch (IllegalArgumentException | IllegalStateException | ClientNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
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
