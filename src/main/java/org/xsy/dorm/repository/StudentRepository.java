package org.xsy.dorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.xsy.dorm.model.student.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByDormId(Long dormId);
    // 统计管理员总数
    @Query("SELECT COUNT(s) FROM Student s")
    long countStudents();

    Optional<Object> findById(Student studentId);
}
