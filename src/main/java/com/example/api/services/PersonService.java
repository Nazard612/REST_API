package com.example.api.services;

import com.example.api.controllers.PersonController;
import com.example.api.models.*;
import com.example.api.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    Logger logger = LoggerFactory.getLogger(PersonController.class);
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final PersonDocumentRepository personDocumentRepository;
    private final PersonContactRepository personContactRepository;
    private final AddressTypeRepository addressTypeRepository;
    private final ContactTypeRepository contactTypeRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final PersonAddressRepository personAddressRepository;

    public PersonService(PersonRepository personRepository, AddressRepository addressRepository, PersonDocumentRepository personDocumentRepository, PersonContactRepository personContactRepository, AddressTypeRepository addressTypeRepository, ContactTypeRepository contactTypeRepository, DocumentTypeRepository documentTypeRepository, PersonAddressRepository personAddressRepository){
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.personDocumentRepository = personDocumentRepository;
        this.personContactRepository = personContactRepository;
        this.addressTypeRepository = addressTypeRepository;
        this.contactTypeRepository = contactTypeRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.personAddressRepository = personAddressRepository;
    }

    public List<PersonModel> getAllPersons(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        logger.info("Получен номер и размер страницы");
        Page<PersonModel> pagedResult = personRepository.findAll(paging);
        logger.info("Данные о всех гражданах получены и отправлены");
        return pagedResult.toList();
    }
    public Optional<PersonModel> getPersonById(String personId){
        UUID humanId = UUID.fromString(personId);
        logger.info("ID гражданина получено");
        Optional<PersonModel> lst = personRepository.findById(humanId);
        logger.info("Данные о гражданине получены и отправлены");
        return lst;
    }
    public void createPerson(PersonModel personModel) {
        PersonModel human = new PersonModel(personModel.getFullName(), personModel.getBirth());
        personRepository.save(human);
        logger.info("ФМО и дата рождения гражданина сохранены");
        for (PersonAddress personAddress : personModel.getPersonAddresses()){
            AddressModel address = personAddress.getAddressNameId();
            AddressTypeModel addressType = personAddress.getAddressTypeId();
            AddressModel resultAddress = new AddressModel(address.getAddress_value());
            addressRepository.save(resultAddress);
            AddressTypeModel checkAddress = addressTypeRepository.findByType(addressType.getAddressName());
            if (checkAddress == null){
                AddressTypeModel resultAddressType = new AddressTypeModel(addressType.getAddressName());
                addressTypeRepository.save(resultAddressType);
                personAddressRepository.save(new PersonAddress(resultAddress, resultAddressType, human));
            }else{
                personAddressRepository.save(new PersonAddress(resultAddress, checkAddress, human));
            }
        }
        logger.info("Данные об адресах гражданина сохранены");
        for (PersonContact personContact : personModel.getPersonContacts()){
            ContactTypeModel contactType = personContact.getContactTypeId();
            ContactTypeModel checkContact = contactTypeRepository.findByType(contactType.getContactName());
            if (checkContact == null){
                ContactTypeModel resultContactType = new ContactTypeModel(contactType.getContactName());
                contactTypeRepository.save(resultContactType);
                personContactRepository.save(new PersonContact(resultContactType, human, personContact.getContactValue()));
            }else{
                personContactRepository.save(new PersonContact(checkContact, human, personContact.getContactValue()));
            }
        }
        logger.info("Данные о контактах гражданина сохранены");
        for (PersonDocument personDocument : personModel.getPersonDocuments()){
            DocumentTypeModel documentType = personDocument.getDocumentTypeId();
            DocumentTypeModel checkDocument = documentTypeRepository.findByType(documentType.getDocumentName());
            if (checkDocument == null){
                DocumentTypeModel resultDocumentType = new DocumentTypeModel(documentType.getDocumentName());
                documentTypeRepository.save(resultDocumentType);
                personDocumentRepository.save(new PersonDocument(resultDocumentType, human, personDocument.getDocumentsValue()));
            }else{
                personDocumentRepository.save(new PersonDocument(checkDocument, human, personDocument.getDocumentsValue()));
            }
        }
        logger.info("Данные о документах гражданина сохранены");
    }
    public void putPersonById(String personId, PersonModel personModel){
        UUID person = UUID.fromString(personId);
        Optional<PersonModel> personMod = personRepository.findById(person);
        logger.info("Неактуальные данные о гражданине получены");
        if (personMod.isPresent()) {
            PersonModel resultPerson = personMod.get();
            resultPerson.setFullName(personModel.getFullName());
            resultPerson.setBirth(personModel.getBirth());
            personRepository.save(resultPerson);
            int count = 0;
            for (PersonAddress personAddress : personModel.getPersonAddresses()){
                List<PersonAddress> listOfAddresses = resultPerson.getPersonAddresses();
                PersonAddress resultPersonAddress = listOfAddresses.get(count);
                AddressModel address= resultPersonAddress.getAddressNameId();
                AddressTypeModel addressType = resultPersonAddress.getAddressTypeId();
                AddressModel resultAddress = personAddress.getAddressNameId();
                AddressTypeModel resultAddressType = personAddress.getAddressTypeId();
                address.setAddress_value(resultAddress.getAddress_value());
                addressType.setAddressName(resultAddressType.getAddressName());
                addressRepository.save(address);
                addressTypeRepository.save(addressType);
                count++;
            }
            logger.info("Данные об адресах гражданина сохранены");
            count=0;
            for (PersonContact personContact : personModel.getPersonContacts()){
                List<PersonContact> listOfPersonContact = resultPerson.getPersonContacts();
                PersonContact resultPersonContact = listOfPersonContact.get(count);
                ContactTypeModel contactType = resultPersonContact.getContactTypeId();
                ContactTypeModel resultContactType = personContact.getContactTypeId();
                contactType.setContactName(resultContactType.getContactName());
                resultPersonContact.setContactValue(personContact.getContactValue());
                contactTypeRepository.save(contactType);
                personContactRepository.save(resultPersonContact);
                count++;
            }
            logger.info("Данные о контактах гражданина сохранены");
            count=0;
            for (PersonDocument personDocument : personModel.getPersonDocuments()){
                List<PersonDocument> listOfPersonDocument = resultPerson.getPersonDocuments();
                PersonDocument resultPersonDocument = listOfPersonDocument.get(count);
                DocumentTypeModel documentType = resultPersonDocument .getDocumentTypeId();
                DocumentTypeModel resultDocumentType = personDocument.getDocumentTypeId();
                documentType.setDocumentName(resultDocumentType.getDocumentName());
                resultPersonDocument.setDocumentsValue(personDocument.getDocumentsValue());
                documentTypeRepository.save(documentType);
                personDocumentRepository.save(resultPersonDocument );
                count++;
            }
            logger.info("Данные о документах гражданина сохранены");
        }
    }
    public void deletePerson(String personId) {
        java.util.UUID person = UUID.fromString(personId);
        Optional<PersonModel> personMod = personRepository.findById(person);
        if (personMod.isPresent()) {
            PersonModel personModel = personMod.get();
            personRepository.deleteById(personModel.getPersonId());
            for (PersonContact personContact : personModel.getPersonContacts()){
                personContactRepository.deleteById(personContact.getContactId());
            }
            logger.info("Данные о контактах гражданина удалены");
            for (PersonAddress personAddress : personModel.getPersonAddresses()){
                AddressModel address = personAddress.getAddressNameId();
                addressRepository.deleteById(address.getAddress_id());
                personAddressRepository.deleteById(personAddress.getPersonAddressId());
            }
            logger.info("Данные об адресах гражданина удалены");
            for (PersonDocument personDocument : personModel.getPersonDocuments()){
                personDocumentRepository.deleteById(personDocument.getDocumentsId());
            }
            logger.info("Данные о документах гражданина удалены");
        }
    }
    public Boolean getVerify(String personName, String personPass) {
        DocumentTypeModel documentTypeModel = personDocumentRepository.findId(personPass);
        Boolean result = null;
        if (documentTypeModel.getDocumentName().equals("Паспорт")){
            PersonModel person = personDocumentRepository.findByValue(personPass);
            result = personName.equals(person.getFullName());
        }
        return result;
    }
}