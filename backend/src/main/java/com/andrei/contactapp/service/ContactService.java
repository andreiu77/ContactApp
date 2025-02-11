package com.andrei.contactapp.service;

import com.andrei.contactapp.domain.Contact;
import com.andrei.contactapp.repository.IContactRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.andrei.contactapp.constant.Constant.IMAGE_DIR;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ContactService {
    private final IContactRepo contactRepo;

    public Page<Contact> getAllContacts(int page, int size) {
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Contact getContactById(String id) {
        return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public Contact createContact(Contact contact) {
        return contactRepo.save(contact);
    }

    public void deleteContact(String id) {
        contactRepo.deleteById(id);
    }

    public String uploadPhoto(String id, MultipartFile file){
        Contact contact = getContactById(id);
        String photoUrl = getUrl.apply(id, file);
        contact.setPhotoUrl(photoUrl);
        contactRepo.save(contact);
        return photoUrl;
    }

    private final Function<String, String> getExtension = filename ->
            Optional.of(filename).filter(name -> name.contains("."))
                    .map(name -> name.substring(name.lastIndexOf("."))).orElse(".png");

    private final BiFunction<String, MultipartFile, String> getUrl = (id, image) -> {
        try {
            String filename = id + getExtension.apply(image.getOriginalFilename());
            Path fileStorageLocation = Paths.get(IMAGE_DIR).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation))
                Files.createDirectories(fileStorageLocation);
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/contactsdb/image/" + filename).toUriString();
        }catch (Exception e){
            throw new RuntimeException("Could not save image");
        }
    };

}
