package com.proyecto.tienda.service.client;

import com.proyecto.tienda.dto.ClientUpdateDTO;
import com.proyecto.tienda.dto.ProductUpdateDTO;
import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.model.Product;

import java.util.List;

public interface IClientService {


        public List<Client> getClients();

        public void saveClient(Client client);

        public void deleteClientById(Long id);

        public Client findClientById(Long id);

        public void editClient(Long id_original, ClientUpdateDTO clientUpdateDTO);

        public void verifyClient(Client client);
}
