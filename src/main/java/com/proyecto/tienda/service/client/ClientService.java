package com.proyecto.tienda.service.client;

import com.proyecto.tienda.dto.ClientUpdateDTO;
import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.repository.IClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {

    private IClientRepository clientRepository;

    //inyeccion de depedencias
    public ClientService(IClientRepository clientRepository) {

            this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getClients() {

        return clientRepository.findAll();
    }

    @Override
    public void saveClient(Client client) {

            clientRepository.save(client);
    }

    @Override
    public void deleteClientById(Long id) {

            clientRepository.deleteById(id);
    }

    @Override
    public Client findClientById(Long id) {

        Client client = clientRepository.findById(id).orElse(null);

        return client;
    }

    @Override
    public void editClient(Long id_original, ClientUpdateDTO clientDto) {

        Client client = this.findClientById(id_original);

        // Actualiza los valores del producto con los datos del DTO
        client.updateFromDTO(clientDto);

        this.saveClient(client);

    }
}
