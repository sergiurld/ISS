package ro.mpp2024.carrenting.service;

import ro.mpp2024.carrenting.domain.Admin;
import ro.mpp2024.carrenting.domain.Car;
import ro.mpp2024.carrenting.domain.Client;
import ro.mpp2024.carrenting.observer.ObservableImplementat;
import ro.mpp2024.carrenting.repository.AdminRepository;
import ro.mpp2024.carrenting.repository.CarRepository;
import ro.mpp2024.carrenting.repository.ClientRepository;
import ro.mpp2024.carrenting.repository.RentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Service extends ObservableImplementat {
    private AdminRepository adminRepository;
    private CarRepository carRepository;
    private ClientRepository clientRepository;
    private RentRepository rentRepository;

    public Service(AdminRepository adminRepository, CarRepository carRepository, ClientRepository clientRepository,  RentRepository rentRepository){
        this.adminRepository = adminRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.rentRepository = rentRepository;
    }

    public List<Car> getAvailableCars() {
        List<Car> allCars = (List<Car>) carRepository.findAll();
        List<Car> availableCars = new ArrayList<>();

        for (Car car : allCars) {
            if ("available".equals(car.getStatus())) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public List<Car> getAllCars(){
        List <Car> allCars = (List<Car>) carRepository.findAll();
        return allCars;
    }
    public Optional<Car> saveCar(String uniqueCode, String carMake, String carModel, String fuel, String vin, Long year, Long km, String status, String color, Long numberOfSeats, Long power) {
        Car newCar = new Car(uniqueCode, carMake, carModel, fuel, vin, year, km, status, color, numberOfSeats, power);
        return carRepository.save(newCar);
    }

    public Admin getAdminByUsername(String username, String password) {
        List<Admin> allAdmins = (List<Admin>) adminRepository.findAll();
        for (Admin admin : allAdmins){
            if(username.equals(admin.getUsername()) && password.equals(admin.getPassword()))
            {
                return admin;
            }
        }
        return null;
    }
    public Client getClientByUsername(String username, String password){
        List<Client> allClients = (List<Client>) clientRepository.findAll();
        for(Client client : allClients){
            if(username.equals(client.getUsername()) && password.equals(client.getPassword()))
            {
                return client;
            }
        }
        return null;
    }
}
