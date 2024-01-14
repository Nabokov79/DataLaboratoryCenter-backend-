package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.Address;

import java.util.Set;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Set<Address> findByCity(String city);

    Address findByCityAndStreetAndHouseNumberAndBuildingNumberAndLetter(String city, String street, Integer houseNumber
                                                                              , Integer buildingNumber, String letter);
}