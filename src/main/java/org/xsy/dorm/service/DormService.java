package org.xsy.dorm.service;

import org.springframework.stereotype.Service;
import org.xsy.dorm.model.dorm.Dorm;
import org.xsy.dorm.repository.DormRepository;

import java.util.List;

@Service
public class DormService {
    private final DormRepository dormRepository;

    public DormService(DormRepository dormRepository) {
        this.dormRepository = dormRepository;
    }

    public List<Dorm> getAllDorms() {
        return dormRepository.findAll();
    }

    public void saveDorm(Dorm dorm) {
        dormRepository.save(dorm);
    }

    public Dorm getDormById(Long id) {
        return dormRepository.findById(id).orElseThrow();
    }

    public void deleteDorm(Long id) {
        dormRepository.deleteById(id);
    }

    public void updateAvailableBeds(Long id,int change) {
        Dorm dorm = getDormById(id);
        int newAvailable  =  dorm.getAvailableBeds()+ change;
        if (newAvailable < 0 || newAvailable > dorm.getTotalBeds()){
            try {
                throw new IllegalAccessException("床位数量不合法");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        dorm.setAvailableBeds(newAvailable);
        dormRepository.save(dorm);
    }

    public Object getAvailableDorms() {
        return dormRepository.findAll();
    }

    public Long countDorms(){
        return dormRepository.countDorms();
    }
}

