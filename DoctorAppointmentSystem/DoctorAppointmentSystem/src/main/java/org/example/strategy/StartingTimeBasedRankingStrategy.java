package org.example.strategy;

import org.example.dto.DoctorSlot;
import org.example.enitities.Doctor;
import org.example.enitities.enums.Specialisation;
import org.example.repository.DoctorRepository;

import java.util.Comparator;
import java.util.List;

public class StartingTimeBasedRankingStrategy implements RankingStrategy {
    private final  DoctorRepository doctorRepository;


    public StartingTimeBasedRankingStrategy(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorSlot> rankDoctors(Specialisation specialisation){
        List<Doctor>doctorList=doctorRepository.getDoctorBySpecialisation(specialisation);
        doctorList.sort((d1,d2)->{
            String earliestSlotsForDoctor1=d1.getAvailableSlots().keySet().stream().min(Comparator.comparing(slot->slot.split("-")[0])).orElse("23:59");
            String earliestSlotsForDoctor2=d2.getAvailableSlots().keySet().stream().min(Comparator.comparing(slot->slot.split("-")[0])).orElse("23:59");
            return earliestSlotsForDoctor1.compareTo(earliestSlotsForDoctor2);
        });
        return doctorList.stream().map(doctor -> new DoctorSlot(doctor.getId(), doctor.getAvailableSlots().keySet().stream().toList())).toList();
    }
}
