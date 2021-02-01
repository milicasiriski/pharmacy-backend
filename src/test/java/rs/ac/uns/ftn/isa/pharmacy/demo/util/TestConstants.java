package rs.ac.uns.ftn.isa.pharmacy.demo.util;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.CreateMedicineReservationParams;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineForm;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.MedicineType;

import java.util.*;

public class TestConstants {
    private TestConstants() {

    }

    private static final long MEDICINE_ID = 1L;
    private static final String MEDICINE_UUID = "uuid";
    private static final String MEDICINE_NAME = "Medicine";
    private static final String MEDICINE_DESCRIPTION = "Description";
    private static final String MEDICINE_MANUFACTURER = "Walter White";
    private static final String MEDICINE_COMPOSITION = "Chemicals";
    private static final double MEDICINE_RATING = 4.5;
    private static final MedicineForm MEDICINE_FORM = MedicineForm.GEL;
    private static final MedicineType MEDICINE_TYPE = MedicineType.HERBAL;
    private static final String MEDICINE_RECOMMENDED_DOSE = "a lot";
    private static final String MEDICINE_SIDE_EFFECTS = "death";
    private static final List<Medicine> MEDICINE_ALTERNATIVES = new ArrayList<>();

    public static Medicine MEDICINE_TEST_OBJECT = new Medicine(MEDICINE_ID, MEDICINE_UUID, MEDICINE_NAME,
            MEDICINE_DESCRIPTION, MEDICINE_MANUFACTURER, MEDICINE_COMPOSITION, MEDICINE_RATING, MEDICINE_FORM,
            MEDICINE_TYPE, true, MEDICINE_RECOMMENDED_DOSE, MEDICINE_SIDE_EFFECTS, 0, MEDICINE_ALTERNATIVES);

    public static MedicineStatus MEDICINE_STATUS_TEST_OBJECT = new MedicineStatus(1, new ArrayList<>());
    public static MedicineStatus MEDICINE_STATUS_STOCK_EMPTY_TEST_OBJECT = new MedicineStatus(0, new ArrayList<>());

    private static final long PHARMACY_ID = 1L;
    private static final String PHARMACY_NAME = "Pharmacy";
    private static final Address PHARMACY_ADDRESS = new Address();
    private static final String PHARMACY_ABOUT = "We sell drugs, but legal.";
    private static final Map<Dermatologist, Employment> PHARMACY_DERMATOLOGISTS = new HashMap<>();
    private static final List<Pharmacist> PHARMACY_PHARMACISTS = new ArrayList<>();
    private static final Map<Medicine, MedicineStatus> PHARMACY_MEDICINE = Map.of(MEDICINE_TEST_OBJECT, MEDICINE_STATUS_TEST_OBJECT);
    private static final Map<Exam, Double> PHARMACY_EXAM_PRICE_LIST = new HashMap<>();
    private static final double PHARMACY_RATING = 3.6;

    public static Pharmacy PHARMACY_TEST_OBJECT = new Pharmacy(PHARMACY_ID, PHARMACY_NAME, PHARMACY_ADDRESS, PHARMACY_ABOUT,
            PHARMACY_DERMATOLOGISTS, PHARMACY_PHARMACISTS, PHARMACY_MEDICINE, PHARMACY_EXAM_PRICE_LIST, PHARMACY_RATING);

    private static final Map<Medicine, MedicineStatus> PHARMACY_MEDICINE_STOCK_EMPTY = new HashMap<>() {{
        put(MEDICINE_TEST_OBJECT, MEDICINE_STATUS_STOCK_EMPTY_TEST_OBJECT);
    }};
    public static Pharmacy PHARMACY_STOCK_EMPTY_TEST_OBJECT = new Pharmacy(PHARMACY_ID, PHARMACY_NAME, PHARMACY_ADDRESS, PHARMACY_ABOUT,
            PHARMACY_DERMATOLOGISTS, PHARMACY_PHARMACISTS, PHARMACY_MEDICINE_STOCK_EMPTY, PHARMACY_EXAM_PRICE_LIST, PHARMACY_RATING);

    public static Pharmacy PHARMACY_MEDICINE_EMPTY_TEST_OBJECT = new Pharmacy(PHARMACY_ID, PHARMACY_NAME, PHARMACY_ADDRESS, PHARMACY_ABOUT,
            PHARMACY_DERMATOLOGISTS, PHARMACY_PHARMACISTS, new HashMap<>(), PHARMACY_EXAM_PRICE_LIST, PHARMACY_RATING);

    public static CreateMedicineReservationParams MEDICINE_RESERVATION_DTO_TEST_OBJECT =
            new CreateMedicineReservationParams(MEDICINE_ID, PHARMACY_ID, new Date());
}
