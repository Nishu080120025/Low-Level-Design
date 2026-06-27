package org.example.strategy;

import org.example.dto.DoctorSlot;
import org.example.enitities.Doctor;
import org.example.enitities.enums.Specialisation;
import org.example.repository.DoctorRepository;

import java.util.List;

public class RatingBasedRankingStrategy implements RankingStrategy {

    private DoctorRepository doctorRepository;

    public RatingBasedRankingStrategy(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorSlot> rankDoctors(Specialisation specialisation) {
        List<Doctor> doctorList = doctorRepository.getDoctorBySpecialisation(specialisation);
        //doctorList.sort((d1,d2)->Double.compare(d2.getRating(),d1.getRating()));
        return doctorList.stream()
                .sorted((d1, d2) -> Double.compare(d2.getRating(), d1.getRating()))
                .map(doctor -> new DoctorSlot(doctor.getId(), doctor.getAvailableSlots().keySet().stream().toList())).toList();
    }
}
