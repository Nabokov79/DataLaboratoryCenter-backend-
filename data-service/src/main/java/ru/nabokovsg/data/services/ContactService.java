package ru.nabokovsg.data.services;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.data.dto.contact.NewContactDto;
import ru.nabokovsg.data.dto.contact.UpdateContactDto;
import ru.nabokovsg.data.models.Contact;

@Validated
public interface ContactService {

    Contact save(@Valid NewContactDto contactDto);

    Contact update(@Valid UpdateContactDto contactDto);
}