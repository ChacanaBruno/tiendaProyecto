package com.proyecto.tienda.service.client;

import com.proyecto.tienda.dto.client.ClientDTO;
import com.proyecto.tienda.dto.client.ClientUpdateDTO;
import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.repository.IClientRepository;
import com.proyecto.tienda.service.exceptions.ClientErrorException;
import org.springframework.stereotype.Service;
import java.util.Optional;

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
    public void saveClient(ClientDTO clientDto) {
        if (clientRepository.existsByDni(clientDto.dni())) {
            throw ClientErrorException.clientDniAlreadyRegistered(clientDto.dni());
        }

        Client client = clientDto.transformToModel();
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

        String oldDni = client.getDni(); // Guardamos el DNI original

        // Actualizamos con los nuevos datos
        client.updateFromDTO(clientDto);

        // Si el DNI fue cambiado, hay que verificar que no esté en uso por otro cliente
        if (!oldDni.equals(client.getDni())) {
            Optional<Client> existing = clientRepository.findByDni(client.getDni());
            if (existing.isPresent()) {
                throw ClientErrorException.clientDniAlreadyRegistered(client.getDni());
            }
        }

        clientRepository.save(client);
    }
    /*@Override
    public void verifyNewClient(Client client) {
        if (!clientRepository.existsByDni(client.getDni())) {
            clientRepository.save(client);
        }
    }
*/
    @Override
    public Client verifyNewClient(ClientDTO clientDto) {
        return clientRepository.findByDni(clientDto.dni())
                .orElseGet(() -> this.saveClient(clientDto));
    }
// continuarrr
}
