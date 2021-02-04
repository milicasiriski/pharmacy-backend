package rs.ac.uns.ftn.isa.pharmacy.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@Embeddable
public class TimeInterval implements Serializable {

    @Column(name = "start_time")
    private Calendar start;

    @Column(name = "end_time")
    private Calendar end;

    public TimeInterval() {

    }

    public TimeInterval(Calendar start, Calendar end) {
        this.start = start;
        this.end = end;
    }

    public TimeInterval(Calendar start, int durationInMinutes) {
        this.start = start;
        Calendar end = Calendar.getInstance();
        end.set(Calendar.YEAR, start.get(Calendar.YEAR));
        end.set(Calendar.MONTH, start.get(Calendar.MONTH));
        end.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH));
        end.set(Calendar.HOUR_OF_DAY, start.get(Calendar.HOUR_OF_DAY));
        end.set(Calendar.MINUTE, start.get(Calendar.MINUTE) + durationInMinutes);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
        this.end = end;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public int getDayOfWeek() {
        return this.start.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeInterval that = (TimeInterval) o;
        return start.equals(that.start) &&
                end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "TimeInterval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public boolean isOverlapping(TimeInterval other) {
        boolean otherStartsInInterval = !other.start.before(this.start) && other.start.before(this.end);
        boolean otherEndsInInterval = !other.end.after(this.end) && other.end.after(this.start);

        boolean startsInOtherInterval = !this.start.before(other.start) && this.start.before(other.end);
        boolean endsInOtherInterval = !this.end.after(other.end) && this.end.after(other.start);

        return otherStartsInInterval || otherEndsInInterval || startsInOtherInterval || endsInOtherInterval;
    }

    public boolean isInside(TimeInterval other) {
        return !this.start.before(other.start) && !this.end.after(other.end);
    }

    public boolean isTimeInside(TimeInterval other) {
        TimeInterval otherNormalized = getNormalized(other);
        return !this.start.before(otherNormalized.start) && !this.end.after(otherNormalized.end);
    }

    private TimeInterval getNormalized(TimeInterval other) {
        Calendar normalizedStart = getNormalized(other.start);
        Calendar normalizedEnd = getNormalized(other.end);
        return new TimeInterval(normalizedStart, normalizedEnd);
    }

    private Calendar getNormalized(Calendar time) {
        Calendar normalized = Calendar.getInstance();
        normalized.set(Calendar.YEAR, start.get(Calendar.YEAR));
        normalized.set(Calendar.MONTH, start.get(Calendar.MONTH));
        normalized.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH));
        normalized.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        normalized.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        normalized.set(Calendar.SECOND, 0);
        normalized.set(Calendar.MILLISECOND, 0);
        return normalized;
    }
}
