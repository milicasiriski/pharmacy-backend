package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.DermatologistHasExamException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.MedicineHasReservationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PharmacistHasExamException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacyService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final PharmacistRepository pharmacistRepository;
    private final DermatologistRepository dermatologistRepository;
    private final MedicineReservationRepository medicineReservationRepository;
    private final ExamRepository examRepository;

    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository, MedicineRepository medicineRepository,
                               PharmacistRepository pharmacistRepository, DermatologistRepository dermatologistRepository,
                               MedicineReservationRepository medicineReservationRepository, ExamRepository examRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.medicineReservationRepository = medicineReservationRepository;
        this.examRepository = examRepository;
    }

    @Override
    public List<PharmacyDto> findAll() {
        List<PharmacyDto> dtoPharmacies = new ArrayList<>();

        pharmacyRepository.findAll().forEach(pharmacy -> dtoPharmacies.add(new PharmacyDto(pharmacy)));

        return dtoPharmacies;
    }

    @Override
    public Pharmacy findPharmacyByPharmacyAdmin(Long pharmacyAdminId) {
        return pharmacyRepository.findPharmacyByPharmacyAdmin(pharmacyAdminId);
    }

    @Override
    public List<PharmacyNameAndAddressDto> findPharmaciesBasicInfo() {
        List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();

        pharmacyRepository.findAll().forEach(pharmacy -> {
            dtoPharmacies.add(new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress().getStreet(), pharmacy.getId()));
        });

        return dtoPharmacies;
    }

    @Override
    public PharmacyProfileDto findPharmacyById(Long pharmacyId) throws EntityNotFoundException {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElse(null);

        if (pharmacy == null) {
            throw new EntityNotFoundException();
        }

        PharmacyDto pharmacyDto = new PharmacyDto(pharmacy);

        List<DermatologistDto> dermatologists = findDermatologists(pharmacy);
        List<PharmacistDto> pharmacists = findPharmacists(pharmacy);
        List<MedicinesBasicInfoDto> medicines = findMedicines(pharmacy);

        return new PharmacyProfileDto(pharmacyDto, dermatologists, pharmacists, medicines);
    }

    @Override
    public PharmacyDto getPharmacyInfoByAdmin() {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = findPharmacyByPharmacyAdmin(pharmacyAdmin.getId());

        return new PharmacyDto(pharmacy);
    }

    @Override
    public void updatePharmacyInfo(PharmacyDto pharmacyDto) {
        Pharmacy pharmacy = findPharmacyByPharmacyAdmin(((PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

        Address address = pharmacy.getAddress();
        AddressDto addressDto = pharmacyDto.getAddress();

        pharmacy.setAbout(pharmacyDto.getAbout());
        pharmacy.setName(pharmacyDto.getName());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setStreet(addressDto.getStreet());
        address.setLatitude(addressDto.getLatitude());
        address.setLongitude(addressDto.getLongitude());

        pharmacyRepository.save(pharmacy);
    }

    @Override
    public void addMedicine(Long medicineId) throws EntityNotFoundException {
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(((PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

        Map<Medicine, MedicineStatus> medicine = pharmacy.getMedicine();
        MedicineStatus medicineStatus = new MedicineStatus(0, new ArrayList<>());

        Medicine newMedicine = medicineRepository.findById(medicineId).orElse(null);
        if (newMedicine == null) {
            throw new EntityNotFoundException();
        }

        medicine.put(newMedicine, medicineStatus);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public void removeMedicine(Long medicineId) throws EntityNotFoundException, MedicineHasReservationException {
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(((PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        Map<Medicine, MedicineStatus> medicines = pharmacy.getMedicine();
        List<MedicineReservation> medicineReservations = medicineReservationRepository.getReservationByPharmacyAndMedicine(medicineId, pharmacy.getId());

        if (!medicineReservations.isEmpty()) {
            throw new MedicineHasReservationException();
        }

        Medicine medicine = medicineRepository.findById(medicineId).orElse(null);
        if (medicine == null) {
            throw new EntityNotFoundException();
        }

        medicines.remove(medicine);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public void removePharmacist(Long pharmacistId) throws EntityNotFoundException, PharmacistHasExamException {
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(((PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        List<Pharmacist> pharmacists = pharmacy.getPharmacists();

        Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId).orElse(null);

        List<Exam> exams = examRepository.getExamByPharmacist(pharmacistId);

        if (!exams.isEmpty()) {
            throw new PharmacistHasExamException();
        }

        if (pharmacist == null) {
            throw new EntityNotFoundException();
        }

        pharmacists.remove(pharmacist);
        pharmacist.setPharmacy(null);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public void removeDermatologist(Long dermatologistId) throws EntityNotFoundException, DermatologistHasExamException {
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(((PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        Map<Dermatologist, Employment> dermatologists = pharmacy.getDermatologists();

        Dermatologist dermatologist = dermatologistRepository.findById(dermatologistId).orElse(null);
        if (dermatologist == null) {
            throw new EntityNotFoundException();
        }

        Employment employment = dermatologist.getPharmacies().get(pharmacy);
        List<Exam> exams = examRepository.getExamByDermatologistEmployment(employment.getId());

        if (!exams.isEmpty()) {
            throw new DermatologistHasExamException();
        }

        dermatologists.remove(dermatologist);
        pharmacyRepository.save(pharmacy);
    }

    private List<MedicinesBasicInfoDto> findMedicines(Pharmacy pharmacy) {
        List<MedicinesBasicInfoDto> medicines = new ArrayList<>();

        pharmacy.getMedicine().keySet().forEach(medicine -> {
            MedicinesBasicInfoDto medicinesBasicInfoDto = new MedicinesBasicInfoDto(medicine.getName(), medicine.getForm().label, medicine.getId(), medicine.getRatings());
            medicines.add(medicinesBasicInfoDto);
        });
        return medicines;
    }

    private List<PharmacistDto> findPharmacists(Pharmacy pharmacy) {
        List<PharmacistDto> pharmacists = new ArrayList<>();

        pharmacy.getPharmacists().forEach(pharmacist -> {
            PharmacistDto pharmacistDto = new PharmacistDto(pharmacist.getName(), pharmacist.getSurname(), 4.5, pharmacist.getId());
            pharmacists.add(pharmacistDto);
        });
        return pharmacists;
    }

    private List<DermatologistDto> findDermatologists(Pharmacy pharmacy) {
        List<DermatologistDto> dermatologists = new ArrayList<>();

        pharmacy.getDermatologists().keySet().forEach(dermatologist -> {
            DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), 4.5, dermatologist.getId());
            dermatologists.add(dermatologistDto);
        });
        return dermatologists;
    }

    @Override
    public Pharmacy save(PharmacyDto dto) {
        // TODO: Give real address to constructor
        Pharmacy pharmacy = new Pharmacy(dto.getName(), new Address(), dto.getAbout());
        pharmacy = pharmacyRepository.save(pharmacy);
        return pharmacy;
    }
}