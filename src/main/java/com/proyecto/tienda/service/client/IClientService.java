package com.proyecto.tienda.service.client;

import com.proyecto.tienda.dto.client.ClientDTO;
import com.proyecto.tienda.dto.client.ClientUpdateDTO;
import com.proyecto.tienda.model.Client;

import java.util.List;

public interface IClientService {


        public List<Client> getClients();

        public void saveClient(ClientDTO clientDto);

        public void deleteClientById(Long id);

        public Client findClientById(Long id);

        public void editClient(Long id_original, ClientUpdateDTO clientUpdateDTO);

        //public void verifyNewClient(Client client);
        public Client verifyNewClient(ClientDTO clientDto);
}
