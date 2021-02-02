package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeIntervalTest {
    private TimeInterval subject;

    @BeforeEach
    void setUp() {
        Calendar start = getDateTime(3000, 3, 3, 10, 30);
        Calendar end = getDateTime(3000, 3, 3, 11, 30);
        subject = new TimeInterval(start, end);
    }

    @Test
    void testIsOverlapping_OtherStartsInTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = getDateTime(3000, 3, 3, 11, 0);
        Calendar end = getDateTime(3000, 3, 3, 12, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherEndsInTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = getDateTime(3000, 3, 3, 9, 0);
        Calendar end = getDateTime(3000, 3, 3, 11, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherStartsAndEndsInTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = getDateTime(3000, 3, 3, 10, 45);
        Calendar end = getDateTime(3000, 3, 3, 11, 15);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherStartsBeforeEndsAfterTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = getDateTime(3000, 3, 3, 9, 0);
        Calendar end = getDateTime(3000, 3, 3, 11, 45);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherStartsAndEndsBeforeTimeInterval_ReturnsFalse() {
        // GIVEN
        Calendar start = getDateTime(3000, 3, 3, 9, 0);
        Calendar end = getDateTime(3000, 3, 3, 10, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsOverlapping_OtherStartsAndEndsAfterTimeInterval_ReturnsFalse() {
        // GIVEN
        Calendar start = getDateTime(3000, 3, 3, 11, 45);
        Calendar end = getDateTime(3000, 3, 3, 13, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsOverlapping_OtherStartsBeforeEndsOnStartOfTimeInterval_ReturnsFalse() {
        // GIVEN
        Calendar start = getDateTime(3000, 3, 3, 9, 0);
        Calendar end = getDateTime(3000, 3, 3, 10, 30);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsOverlapping_OtherStartsOnEndEndsAfterTimeInterval_ReturnsFalse() {
        // GIVEN
        Calendar start = getDateTime(3000, 3, 3, 11, 30);
        Calendar end = getDateTime(3000, 3, 3, 13, 30);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsOverlapping_OtherEqualsTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = getDateTime(3000, 3, 3, 10, 30);
        Calendar end = getDateTime(3000, 3, 3, 11, 30);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherIsOnDifferentDay_ReturnsFalse() {
        // GIVEN
        Calendar start = getDateTime(3002, 2, 5, 11, 0);
        Calendar end = getDateTime(3000, 3, 3, 12, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testContainsTime_IsInInterval_ReturnsTrue() {
        // GIVEN
        Calendar time = getDateTime(2021, 2, 5, 11, 0);

        // WHEN
        boolean result = subject.containsTime(time);

        // THEN
        assertTrue(result);
    }

    @Test
    void testContainsTime_IsBeforeInterval_ReturnsFalse() {
        // GIVEN
        Calendar time = getDateTime(2021, 2, 5, 10, 0);

        // WHEN
        boolean result = subject.containsTime(time);

        // THEN
        assertFalse(result);
    }

    @Test
    void testContainsTime_IsAfterInterval_ReturnsFalse() {
        // GIVEN
        Calendar time = getDateTime(2021, 2, 5, 12, 0);

        // WHEN
        boolean result = subject.containsTime(time);

        // THEN
        assertFalse(result);
    }

    private Calendar getDateTime(int year, int month, int day, int hour, int minute) {
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
