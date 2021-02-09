package rs.ac.uns.ftn.isa.pharmacy.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.DermatologistHasExamException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.DermatologistHasShiftInAnotherPharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.MedicineHasReservationException;
import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.PharmacistHasExamException;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.DaysOfWeek;
import rs.ac.uns.ftn.isa.pharmacy.demo.repository.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.service.PharmacyService;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.RatingFilter;

import javax.persistence.EntityNotFoundException;
import java.util.*;

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
    public List<PharmacyDto> findAll(RatingFilter ratingFilter, double distance, double userLon, double userLat) {
        int rating = ratingFilter.ordinal();
        Iterable<Pharmacy> pharmacies = pharmacyRepository.findWithRatingGreaterThan(rating);

        List<PharmacyDto> result = new ArrayList<>();
        pharmacies.forEach(pharmacy -> {
            double calculatedDistance = calculateDistanceInKilometers(pharmacy.getAddress(), userLon, userLat);
            if (calculatedDistance <= distance) {
                result.add(new PharmacyDto(pharmacy));
            }
        });
        return result;
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
    public PharmacyProfileDto getAllPharmacyInfoByPharmacyAdmin() throws EntityNotFoundException {
        PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyAdmin.getPharmacy().getId()).orElse(null);

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
    public Pharmacy save(PharmacyDto dto) {
        // TODO: Give real address to constructor
        Pharmacy pharmacy = new Pharmacy(dto.getName(), new Address(), dto.getAbout());
        pharmacy = pharmacyRepository.save(pharmacy);
        return pharmacy;
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

    @Override
    public void addDermatologist(AddDermatologistDto addDermatologistDto) throws EntityNotFoundException, DermatologistHasShiftInAnotherPharmacy {
        Dermatologist dermatologist = dermatologistRepository.findById(addDermatologistDto.getDermatologistId()).orElse(null);
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByPharmacyAdmin(((PharmacyAdmin)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

        if (dermatologist == null) {
            throw new EntityNotFoundException();
        }
        Map<DaysOfWeek, TimeInterval> convertedShifts = generateShifts(addDermatologistDto.getShifts());
        Collection<Employment> employments = dermatologist.getPharmacies().values();

        employments.forEach(employment -> {
            Map<DaysOfWeek, TimeInterval> shifts = employment.getShifts();
            checkIfIntervalsOverlapping(convertedShifts, shifts);
        });

        Employment newEmployment = new Employment(convertedShifts, 20.0, 20, new ArrayList<>());
        pharmacy.addDermatologist(dermatologist, newEmployment);
        pharmacyRepository.save(pharmacy);
    }

    private void checkIfIntervalsOverlapping(Map<DaysOfWeek, TimeInterval> convertedShifts, Map<DaysOfWeek, TimeInterval> shifts) {
        if ((convertedShifts.containsKey(DaysOfWeek.MONDAY) && shifts.get(DaysOfWeek.MONDAY).isOverlapping(convertedShifts.get(DaysOfWeek.MONDAY)))
                || (convertedShifts.containsKey(DaysOfWeek.TUESDAY) && shifts.get(DaysOfWeek.TUESDAY).isOverlapping(convertedShifts.get(DaysOfWeek.TUESDAY)))
                || (convertedShifts.containsKey(DaysOfWeek.WEDNESDAY) && shifts.get(DaysOfWeek.WEDNESDAY).isOverlapping(convertedShifts.get(DaysOfWeek.WEDNESDAY)))
                || (convertedShifts.containsKey(DaysOfWeek.THURSDAY) && shifts.get(DaysOfWeek.THURSDAY).isOverlapping(convertedShifts.get(DaysOfWeek.THURSDAY)))
                || (convertedShifts.containsKey(DaysOfWeek.FRIDAY) && shifts.get(DaysOfWeek.FRIDAY).isOverlapping(convertedShifts.get(DaysOfWeek.FRIDAY)))
                || (convertedShifts.containsKey(DaysOfWeek.SATURDAY) && shifts.get(DaysOfWeek.SATURDAY).isOverlapping(convertedShifts.get(DaysOfWeek.SATURDAY)))
                || (convertedShifts.containsKey(DaysOfWeek.SUNDAY) && shifts.get(DaysOfWeek.SUNDAY).isOverlapping(convertedShifts.get(DaysOfWeek.SUNDAY)))) {
            throw new DermatologistHasShiftInAnotherPharmacy();
        }
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

    private Map<DaysOfWeek, TimeInterval> generateShifts(List<TimeIntervalDto> timeIntervals) {
        Map<DaysOfWeek, TimeInterval> shifts = new HashMap<>();
        for (int i = 0; i <= 6; i++) {
            if (timeIntervals.get(i).getShiftDefined()) {
                TimeInterval shift = generateShiftTimeInterval(timeIntervals.get(i));
                shifts.put(DaysOfWeek.values()[i], shift);
            }
        }

        return shifts;
    }

    private TimeInterval generateShiftTimeInterval(TimeIntervalDto timeIntervalDto) {
        Calendar shiftStart = Calendar.getInstance();
        Calendar shiftEnd = Calendar.getInstance();

        shiftStart.setTime(timeIntervalDto.getStart());
        shiftStart.set(Calendar.SECOND, 0);
        shiftStart.set(Calendar.MILLISECOND, 0);

        shiftEnd.setTime(timeIntervalDto.getEnd());
        shiftEnd.set(Calendar.SECOND, 0);
        shiftEnd.set(Calendar.MILLISECOND, 0);

        return new TimeInterval(shiftStart, shiftEnd);
    }

    private double calculateDistanceInKilometers(Address pharmacyAddress, double userLon, double userLat) {
        double degToKmFactor = 10000.0 / 90.0;
        double pharmacyLon = pharmacyAddress.getLongitude();
        double pharmacyLat = pharmacyAddress.getLatitude();

        double deltaLat = (userLat - pharmacyLat) * degToKmFactor;
        double deltaLon = (userLon - pharmacyLon) * degToKmFactor;
        return Math.sqrt(deltaLat * deltaLat + deltaLon * deltaLon);
    }
}