package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.contact.NewContactDto;
import ru.nabokovsg.data.dto.contact.UpdateContactDto;
import ru.nabokovsg.data.models.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact mapToNewContact(NewContactDto contactDto);

    Contact mapToUpdateContact(UpdateContactDto contactDto);
}