package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.address.AddressDto;
import ru.nabokovsg.data.dto.address.NewAddressDto;
import ru.nabokovsg.data.dto.address.UpdateAddressDto;
import ru.nabokovsg.data.models.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address mapToNewAddress(NewAddressDto addressDto);

    AddressDto mapToAddressDto(Address address);

    Address mapToUpdateAddress(UpdateAddressDto addressDto);
}