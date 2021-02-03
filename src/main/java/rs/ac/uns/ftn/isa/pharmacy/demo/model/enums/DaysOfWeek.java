package rs.ac.uns.ftn.isa.pharmacy.demo.model.enums;

import rs.ac.uns.ftn.isa.pharmacy.demo.exceptions.DayOfWeekCouldNotBeConvertedFromCalendarException;

public enum DaysOfWeek {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    public final String label;

    DaysOfWeek(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static DaysOfWeek fromCalendarDayOfWeek(int value) {
        DaysOfWeek result;
        switch (value) {
            case 1:
                result = SUNDAY;
                break;
            case 2:
                result = MONDAY;
                break;
            case 3:
                result = TUESDAY;
                break;
            case 4:
                result = WEDNESDAY;
                break;
            case 5:
                result = THURSDAY;
                break;
            case 6:
                result = FRIDAY;
                break;
            case 7:
                result = SATURDAY;
                break;
            default:
                throw new DayOfWeekCouldNotBeConvertedFromCalendarException();
        }
        return result;
    }
}
