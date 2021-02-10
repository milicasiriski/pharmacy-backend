package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.ac.uns.ftn.isa.pharmacy.demo.util.TestConstants;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeIntervalTest {
    private TimeInterval subject;

    @BeforeEach
    void setUp() {
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 10, 30);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 11, 30);
        subject = new TimeInterval(start, end);
    }

    @Test
    void testIsOverlapping_OtherStartsInTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 11, 0);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 12, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherEndsInTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 9, 0);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 11, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherStartsAndEndsInTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 10, 45);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 11, 15);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherStartsBeforeEndsAfterTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 9, 0);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 11, 45);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherStartsAndEndsBeforeTimeInterval_ReturnsFalse() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 9, 0);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 10, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsOverlapping_OtherStartsAndEndsAfterTimeInterval_ReturnsFalse() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 11, 45);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 13, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsOverlapping_OtherStartsBeforeEndsOnStartOfTimeInterval_ReturnsFalse() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 9, 0);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 10, 30);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsOverlapping_OtherStartsOnEndEndsAfterTimeInterval_ReturnsFalse() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 11, 30);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 13, 30);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsOverlapping_OtherEqualsTimeInterval_ReturnsTrue() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3000, 3, 3, 10, 30);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 11, 30);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertTrue(result);
    }

    @Test
    void testIsOverlapping_OtherIsOnDifferentDay_ReturnsFalse() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(3002, 2, 5, 11, 0);
        Calendar end = TestConstants.getDateTime(3000, 3, 3, 12, 0);
        TimeInterval other = new TimeInterval(start, end);

        // WHEN
        boolean result = subject.isOverlapping(other);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsInside_StartingBeforeShift_ReturnsFalse() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(2021, 3, 5, 7, 0);
        Calendar end = TestConstants.getDateTime(2021, 3, 5, 8, 20);
        TimeInterval newExam = new TimeInterval(start, end);

        Calendar startWork = TestConstants.getDateTime(2021, 3, 5, 8, 0);
        Calendar endWork = TestConstants.getDateTime(2021, 3, 5, 10, 0);
        TimeInterval workTime = new TimeInterval(startWork, endWork);

        // WHEN
        boolean result = newExam.isInside(workTime);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsInside_EndingAfterShift_ReturnsFalse() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(2021, 3, 5, 9, 0);
        Calendar end = TestConstants.getDateTime(2021, 3, 5, 10, 20);
        TimeInterval newExam = new TimeInterval(start, end);

        Calendar startWork = TestConstants.getDateTime(2021, 3, 5, 8, 0);
        Calendar endWork = TestConstants.getDateTime(2021, 3, 5, 10, 0);
        TimeInterval workTime = new TimeInterval(startWork, endWork);

        // WHEN
        boolean result = newExam.isInside(workTime);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsInside_IsNotInShift_ReturnsFalse() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(2021, 3, 5, 11, 0);
        Calendar end = TestConstants.getDateTime(2021, 3, 5, 11, 20);
        TimeInterval newExam = new TimeInterval(start, end);

        Calendar startWork = TestConstants.getDateTime(2021, 3, 5, 8, 0);
        Calendar endWork = TestConstants.getDateTime(2021, 3, 5, 10, 0);
        TimeInterval workTime = new TimeInterval(startWork, endWork);

        // WHEN
        boolean result = newExam.isInside(workTime);

        // THEN
        assertFalse(result);
    }

    @Test
    void testIsInside_IsInShift_ReturnsTrue() {
        // GIVEN
        Calendar start = TestConstants.getDateTime(2021, 3, 5, 8, 10);
        Calendar end = TestConstants.getDateTime(2021, 3, 5, 8, 45);
        TimeInterval newExam = new TimeInterval(start, end);

        Calendar startWork = TestConstants.getDateTime(2021, 3, 5, 8, 0);
        Calendar endWork = TestConstants.getDateTime(2021, 3, 5, 10, 0);
        TimeInterval workTime = new TimeInterval(startWork, endWork);

        // WHEN
        boolean result = newExam.isInside(workTime);

        // THEN
        assertTrue(result);
    }
}
