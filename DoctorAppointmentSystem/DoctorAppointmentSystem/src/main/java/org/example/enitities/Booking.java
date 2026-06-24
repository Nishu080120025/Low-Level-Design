package org.example.enitities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.enitities.enums.BookingStatus;
import org.example.util.IdUtil;

@Getter
@Setter
@Data
public class Booking {
    private String bookingId;
    private String slot;
    private String doctorId;
    private String patientId;
    private BookingStatus status;

    public Booking(String slot, String doctorId, String patientId,BookingStatus status) {
        this.bookingId = IdUtil.generateId();
        this.slot = slot;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.status=status;
    }
}
