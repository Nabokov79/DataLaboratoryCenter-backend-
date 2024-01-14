package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.address.AddressDto;
import ru.nabokovsg.data.dto.address.NewAddressDto;
import ru.nabokovsg.data.dto.address.UpdateAddressDto;
import ru.nabokovsg.data.models.Address;

import java.util.List;

public interface AddressService {

    AddressDto save(NewAddressDto addressDto);

    AddressDto update(UpdateAddressDto addressDto);

    Address get(Long id);

    List<AddressDto> getAll(String city, int from, int size);

    String delete(Long id);
}