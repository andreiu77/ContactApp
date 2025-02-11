package com.andrei.contactapp.repository;

import com.andrei.contactapp.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContactRepo extends JpaRepository<Contact, String> {
    Optional<Contact> findById(String id);
}
