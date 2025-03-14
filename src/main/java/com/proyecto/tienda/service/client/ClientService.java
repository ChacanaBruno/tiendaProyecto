package com.proyecto.tienda.service.client;

import com.proyecto.tienda.dto.ClientUpdateDTO;
import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.repository.IClientRepository;
import com.proyecto.tienda.repository.exception.ClientNotFoundException;
import com.proyecto.tienda.service.exceptions.TotalAmountErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void verifyClient(Client client) {
        if (!clientRepository.existsById(client.getId_client())) {
            throw new ClientNotFoundException("the client with " + client.getId_client() + "does not exist");
        }
    }

}
