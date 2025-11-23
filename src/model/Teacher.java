package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Teacher {
    private String lastName;
    private String firstName;
    private String bookedTime;
    private LocalDateTime exactTimeOfBooking;

    public Teacher(String lastName, String firstName, String bookedTime, LocalDateTime exactTimeOfBooking) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.bookedTime = bookedTime;
        this.exactTimeOfBooking = exactTimeOfBooking;
    }

    public String getFullName() {
        return lastName+" "+firstName;
    }

    public String getNormalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm");
        return exactTimeOfBooking.format(formatter);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBookedTime() {
        return bookedTime;
    }

    public LocalDateTime getExactTimeOfBooking() {
        return exactTimeOfBooking;
    }
}
