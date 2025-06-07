package org.xsy.dorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.xsy.dorm.model.dorm.Dorm;


import java.util.List;

public interface DormRepository extends JpaRepository<Dorm, Long> {
    List<Dorm> findByBuildingNameContaining(String keyWord);

    //统计宿舍总数
    @Query("SELECT COUNT(d) FROM Dorm d")
    long countDorms();
}
