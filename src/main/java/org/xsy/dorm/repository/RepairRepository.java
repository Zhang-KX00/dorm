package org.xsy.dorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xsy.dorm.model.dorm.Dorm;
import org.xsy.dorm.model.repair.Repair;
import org.xsy.dorm.model.student.Student;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {
//    List<Repair> findByDormId(Dorm dormId); //通过宿舍id查询报修的东西
//    List<Repair> findByStudentId(Student studentId); //通过学生id查询报修的内容


}
