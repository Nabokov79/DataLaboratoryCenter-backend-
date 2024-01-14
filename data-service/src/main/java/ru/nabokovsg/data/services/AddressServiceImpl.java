package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.address.AddressDto;
import ru.nabokovsg.data.dto.address.NewAddressDto;
import ru.nabokovsg.data.dto.address.UpdateAddressDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.AddressMapper;
import ru.nabokovsg.data.models.Address;
import ru.nabokovsg.data.repository.AddressRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;

    @Override
    public AddressDto save(NewAddressDto addressDto) {
        Address addresses = repository.findByCityAndStreetAndHouseNumberAndBuildingNumberAndLetter(
                                                             addressDto.getCity(), addressDto.getStreet()
                                                           , addressDto.getHouseNumber(), addressDto.getBuildingNumber()
                                                           , addressDto.getLetter());
        return mapper.mapToAddressDto(
                Objects.requireNonNullElseGet(addresses, () -> repository.save(mapper.mapToNewAddress(addressDto)))
        );
    }

    @Override
    public AddressDto update(UpdateAddressDto addressDto) {
        if (!repository.existsById(addressDto.getId())) {
            throw new NotFoundException(String.format("Address with id=%s not found for update.", addressDto.getId()));
        }
        return mapper.mapToAddressDto(repository.save(mapper.mapToUpdateAddress(addressDto)));
    }

    @Override
    public Address get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Address with id=%s not found", id)));
    }

    @Override
    public List<AddressDto> getAll(String city, int from, int size) {
        Pageable pageable = PageRequest.of(from / size,size, Sort.by("city"));
        if(city != null) {
            return repository.findByCity(city).stream().map(mapper::mapToAddressDto).toList();
        }
        return repository.findAll(pageable).getContent().stream().map(mapper::mapToAddressDto).toList();
    }

    @Override
    public String delete(Long id) {
        Address address = repository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("Address with id=%s not found for delete.", id)));
        repository.deleteById(id);
        return String.join(" ", address.getCity(),
                                                 address.getStreet(),
                                                 address.getHouseNumber().toString());
    }
}