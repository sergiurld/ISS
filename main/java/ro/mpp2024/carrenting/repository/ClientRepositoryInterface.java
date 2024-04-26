package ro.mpp2024.carrenting.repository;

import ro.mpp2024.carrenting.domain.Client;

public interface ClientRepositoryInterface extends Repository<Long, Client>{

    public Long findClientUniqueCode(String uniqueCode);
}
