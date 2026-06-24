package org.example.strategy;

import org.example.dto.DoctorSlot;
import org.example.enitities.enums.Specialisation;

import java.util.List;

public interface RankingStrategy {

    List<DoctorSlot> rankDoctors(Specialisation specialisation);
}
