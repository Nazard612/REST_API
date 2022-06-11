package com.example.api.services;

import com.example.api.models.*;
import com.example.api.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    @Resource
    private final PersonRepository personRepository;
    @Resource
    private final AddressRepository addressRepository;
    @Resource
    private final PersonDocumentRepository personDocumentRepository;
    @Resource
    private final PersonContactRepository personContactRepository;
    @Resource
    private final AddressTypeRepository addressTypeRepository;
    @Resource
    private final ContactTypeRepository contactTypeRepository;
    @Resource
    private final DocumentTypeRepository documentTypeRepository;
    @Resource
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

    public List<PersonModel> getAllPersons() {
        return personRepository.findAll();
    }
    public Optional<PersonModel> getPersonById(String personId){
        java.util.UUID q = UUID.fromString(personId);
        return personRepository.findById(q);
    }
    public void createPerson(PersonModel personModel) {
        PersonModel end_pm = new PersonModel(personModel.getFull_name(), personModel.getBirth());
        personRepository.save(end_pm);
        for (PersonAddress pa : personModel.getPerson_addresses()){
            AddressModel ad = pa.getAddress_name_id();
            AddressTypeModel at = pa.getAddress_type_id();
            AddressModel end_am = new AddressModel(ad.getAddress_value());
            addressRepository.save(end_am);
            AddressTypeModel end_at = new AddressTypeModel(at.getAddress_name());
            addressTypeRepository.save(end_at);
            personAddressRepository.save(new PersonAddress(end_am, end_at, end_pm));
        }
        for (PersonContact pc : personModel.getPerson_contacts()){
            ContactTypeModel ct = pc.getContact_type_id();
            ContactTypeModel end_ct = new ContactTypeModel(ct.getContact_name());
            contactTypeRepository.save(end_ct);
            personContactRepository.save(new PersonContact(end_ct, end_pm, pc.getContact_value()));
        }
        for (PersonDocument pd : personModel.getPerson_documents()){
            DocumentTypeModel dt = pd.getDocument_type_id();
            DocumentTypeModel end_pd = new DocumentTypeModel(dt.getDocument_name());
            documentTypeRepository.save(end_pd);
            personDocumentRepository.save(new PersonDocument(end_pd, end_pm, pd.getDocuments_value()));
        }
    }
    public void putPersonById(String personId, PersonModel personModel){
        java.util.UUID q = UUID.fromString(personId);
        Optional<PersonModel> p = personRepository.findById(q);
        if (p.isPresent()) {
            PersonModel _p = p.get();
            _p.setFull_name(personModel.getFull_name());
            _p.setBirth(personModel.getBirth());
            personRepository.save(_p);
            int count = 0;
            for (PersonAddress pa : personModel.getPerson_addresses()){
                List<PersonAddress> lst= _p.getPerson_addresses();
                PersonAddress end_pa = lst.get(count);
                AddressModel ad = end_pa.getAddress_name_id();
                AddressTypeModel at = end_pa.getAddress_type_id();
                AddressModel new_ad = pa.getAddress_name_id();
                AddressTypeModel new_at = pa.getAddress_type_id();
                ad.setAddress_value(new_ad.getAddress_value());
                at.setAddress_name(new_at.getAddress_name());
                addressRepository.save(ad);
                addressTypeRepository.save(at);
                count++;
            }
            count=0;
            for (PersonContact pc : personModel.getPerson_contacts()){
                List<PersonContact> lst_c= _p.getPerson_contacts();
                PersonContact end_pc = lst_c.get(count);
                ContactTypeModel ct = end_pc.getContact_type_id();
                ContactTypeModel new_ct = pc.getContact_type_id();
                ct.setContact_name(new_ct.getContact_name());
                end_pc.setContact_value(pc.getContact_value());
                contactTypeRepository.save(ct);
                personContactRepository.save(end_pc);
                count++;
            }
            count=0;
            for (PersonDocument pd : personModel.getPerson_documents()){
                List<PersonDocument> lst_d= _p.getPerson_documents();
                PersonDocument end_pd = lst_d.get(count);
                DocumentTypeModel dt = end_pd.getDocument_type_id();
                DocumentTypeModel new_dt = pd.getDocument_type_id();
                dt.setDocument_name(new_dt.getDocument_name());
                end_pd.setDocuments_value(pd.getDocuments_value());
                documentTypeRepository.save(dt);
                personDocumentRepository.save(end_pd);
                count++;
            }
        }
    }
    public void deletePerson(String personId) {
        java.util.UUID q = UUID.fromString(personId);
        Optional<PersonModel> p = personRepository.findById(q);
        if (p.isPresent()) {
            PersonModel _p = p.get();
            personRepository.deleteById(_p.getPerson_id());
            for (PersonContact pc : _p.getPerson_contacts()){
                ContactTypeModel new_ct = pc.getContact_type_id();
                contactTypeRepository.deleteById(new_ct.getContact_type_id());
                personContactRepository.deleteById(pc.getContact_id());
            }
            for (PersonAddress pa : _p.getPerson_addresses()){
                AddressModel new_ad = pa.getAddress_name_id();
                AddressTypeModel new_at = pa.getAddress_type_id();
                addressRepository.deleteById(new_ad.getAddress_id());
                addressTypeRepository.deleteById(new_at.getAddress_type_id());
                personAddressRepository.deleteById(pa.getPerson_address_id());
            }
            for (PersonDocument pd : _p.getPerson_documents()){
                DocumentTypeModel new_dt = pd.getDocument_type_id();
                documentTypeRepository.deleteById(new_dt.getDocument_type_id());
                personDocumentRepository.deleteById(pd.getDocuments_id());
            }
        }
    }
    public Boolean getVerify(String personName, String personPass) {
        DocumentTypeModel dt = personDocumentRepository.findId(personPass);
        Boolean result = null;
        if (dt.getDocument_name().equals("Паспорт")){
            PersonModel p = personDocumentRepository.findByValue(personPass);
            result = personName.equals(p.getFull_name());
        }
        return result;
    }
}