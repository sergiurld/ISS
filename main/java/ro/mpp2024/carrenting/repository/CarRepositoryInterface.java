package ro.mpp2024.carrenting.repository;

import ro.mpp2024.carrenting.domain.Car;

public interface CarRepositoryInterface extends Repository<Long, Car>{
    public Long findCarUniqueCode(String uniqueCode);

}
