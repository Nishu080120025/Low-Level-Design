package org.example.enitities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.util.IdUtil;

@Setter
@Getter
@Data
public class Patient {
    private String id;
    private String name;

    public Patient(String id, String name) {
        this.id = IdUtil.generateId();
        this.name = name;
    }
}

