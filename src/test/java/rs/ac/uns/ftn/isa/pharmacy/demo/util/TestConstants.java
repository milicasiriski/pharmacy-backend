package rs.ac.uns.ftn.isa.pharmacy.demo.util;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.*;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.AddDermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.CreateMedicineReservationParamsDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.TimeIntervalDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.enums.DaysOfWeek;
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
    private static final long PHARMACY_2_ID = 2L;
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

    public static Pharmacy PHARMACY_TEST_OBJECT_2 = new Pharmacy(PHARMACY_2_ID, PHARMACY_NAME, PHARMACY_ADDRESS, PHARMACY_ABOUT,
            PHARMACY_DERMATOLOGISTS, PHARMACY_PHARMACISTS, PHARMACY_MEDICINE, PHARMACY_EXAM_PRICE_LIST, PHARMACY_RATING);

    private static final Map<Medicine, MedicineStatus> PHARMACY_MEDICINE_STOCK_EMPTY = new HashMap<>() {{
        put(MEDICINE_TEST_OBJECT, MEDICINE_STATUS_STOCK_EMPTY_TEST_OBJECT);
    }};
    public static Pharmacy PHARMACY_STOCK_EMPTY_TEST_OBJECT = new Pharmacy(PHARMACY_ID, PHARMACY_NAME, PHARMACY_ADDRESS, PHARMACY_ABOUT,
            PHARMACY_DERMATOLOGISTS, PHARMACY_PHARMACISTS, PHARMACY_MEDICINE_STOCK_EMPTY, PHARMACY_EXAM_PRICE_LIST, PHARMACY_RATING);

    public static Pharmacy PHARMACY_MEDICINE_EMPTY_TEST_OBJECT = new Pharmacy(PHARMACY_ID, PHARMACY_NAME, PHARMACY_ADDRESS, PHARMACY_ABOUT,
            PHARMACY_DERMATOLOGISTS, PHARMACY_PHARMACISTS, new HashMap<>(), PHARMACY_EXAM_PRICE_LIST, PHARMACY_RATING);

    public static CreateMedicineReservationParamsDto MEDICINE_RESERVATION_DTO_TEST_OBJECT =
            new CreateMedicineReservationParamsDto(MEDICINE_ID, PHARMACY_ID, new Date());

    public static Map<DaysOfWeek, TimeInterval> SHIFTS = new HashMap<>() {{
        put(DaysOfWeek.MONDAY, new TimeInterval(getDateTime(2021, 3, 5, 10, 0), getDateTime(2021, 3, 5, 12, 0)));
    }};

    private static final Date START = getDateTime(2021, 3, 5, 10, 0).getTime();
    private static final Date END = getDateTime(2021, 3, 5, 12, 0).getTime();

    public static List<TimeIntervalDto> SHIFTS_2 = List.of(new TimeIntervalDto(START, END, false),
            new TimeIntervalDto(START, END, true),
            new TimeIntervalDto(START, END, true),
            new TimeIntervalDto(START, END, true),
            new TimeIntervalDto(START, END, true),
            new TimeIntervalDto(START, END, true),
            new TimeIntervalDto(START, END, true));

    public static final Employment EMPLOYMENT = new Employment(SHIFTS, new ArrayList<>());

    public static Map<Pharmacy, Employment> PHARMACIES = new HashMap<>() {{
        put(PHARMACY_TEST_OBJECT_2, EMPLOYMENT);
    }};

    public static final Dermatologist DERMATOLOGIST = new Dermatologist("email@email", "123", "Pera", "Peric", PHARMACIES, 3);
    public static final PharmacyAdmin PHARMACY_ADMIN = new PharmacyAdmin("admin@admin", "123", PHARMACY_TEST_OBJECT, "Djuro", "Djuric");
    public static final AddDermatologistDto ADD_DERMATOLOGIST_DTO = new AddDermatologistDto(1L, SHIFTS_2);

    public static Calendar getDateTime(int year, int month, int day, int hour, int minute) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.HOUR, hour);
        date.set(Calendar.MINUTE, minute);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
    }
}
