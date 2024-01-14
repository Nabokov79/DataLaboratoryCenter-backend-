package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}