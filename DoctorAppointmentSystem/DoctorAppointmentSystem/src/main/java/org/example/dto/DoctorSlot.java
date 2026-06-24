package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class DoctorSlot {
    private String doctorId;
    private List<String> slot;

    public DoctorSlot(String doctorId, List<String> slot) {
        this.doctorId = doctorId;
        this.slot = slot;
    }
}
