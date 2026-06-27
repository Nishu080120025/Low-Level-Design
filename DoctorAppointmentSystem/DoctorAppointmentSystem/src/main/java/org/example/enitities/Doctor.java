package org.example.enitities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.enitities.enums.Specialisation;
import org.example.util.IdUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Data
public class Doctor {
    private String id;
    private String name;
    private Specialisation specialisation;
    Map<String, Boolean> availableSlots;
    private Double rating;

    public Doctor(String name, Specialisation specialisation, Double rating, Map<String, Boolean> availableSlots) {
        this.id = IdUtil.generateId();
        this.name = name;
        this.specialisation = specialisation;
        this.availableSlots = availableSlots;
        this.rating = rating;
    }
}
