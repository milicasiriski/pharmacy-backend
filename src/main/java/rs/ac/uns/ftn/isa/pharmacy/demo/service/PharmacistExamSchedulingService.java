package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Exam;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Patient;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistExamDTO;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.GetPharmacistExamResponse;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.SchedulePharmacistExamParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.mapping.ExamDetails;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PharmacistSortType;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.PharmacySortType;

import javax.mail.MessagingException;
import java.util.Date;

public interface PharmacistExamSchedulingService {
    Iterable<Pharmacy> getPharmaciesWithAvailableAppointments(Date dateTime, PharmacySortType sortType);

    Iterable<Pharmacist> getPharmacistsWithAvailableAppointments(Date dateTime, long pharmacyId, PharmacistSortType sortType);

    void scheduleAppointment(SchedulePharmacistExamParams params, Patient patient) throws MessagingException;
    void scheduleAppointmentForPharmacist(long examId, String patientID, Pharmacist pharmacist) throws MessagingException;
    void createAppointmentForPharmacist(DermatologistExamDTO dermatologistExamDTO, Pharmacist pharmacist);

    void cancelAppointment(long examId, Patient signedInUser);

    Iterable<ExamDetails> getScheduledPharmacistExamsForPatient(Patient patient);

    Iterable<GetPharmacistExamResponse> getAllScheduledPharmacistExamsForPatient(Pharmacist e);

    Iterable<ExamDetails> getPharmacistExamHistoryForPatient(Patient patient);
}
