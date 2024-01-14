package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.contact.NewContactDto;
import ru.nabokovsg.data.dto.contact.UpdateContactDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.ContactMapper;
import ru.nabokovsg.data.models.Contact;
import ru.nabokovsg.data.repository.ContactRepository;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ContactMapper mapper;

    @Override
    public Contact save(NewContactDto contactDto) {
        return repository.save(mapper.mapToNewContact(contactDto));
    }

    @Override
    public Contact update(UpdateContactDto contactDto) {
        if (repository.existsById(contactDto.getId())) {
            return repository.save(mapper.mapToUpdateContact(contactDto));
        }
        throw new NotFoundException(String.format("Requisites with id=%s not found for update", contactDto.getId()));
    }
}
