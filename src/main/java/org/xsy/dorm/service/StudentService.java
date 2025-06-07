package org.xsy.dorm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xsy.dorm.model.dorm.Dorm;
import org.xsy.dorm.model.student.Student;
import org.xsy.dorm.repository.DormRepository;
import org.xsy.dorm.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final DormRepository dormRepository;
    private final DormService dormService;

    public void saveStudent(Student student, long dormId) {
        Dorm dorm = dormRepository.findById(dormId)
                .orElseThrow(() -> new IllegalArgumentException("无效的宿舍ID"));
        if (dorm.getAvailableBeds() < 1) {
            throw new RuntimeException("该宿舍已满员");
        }
        student.setDorm(dorm);
        studentRepository.save(student);
        dormService.updateAvailableBeds(dormId, -1);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByDorm(Long id) {
        return studentRepository.findByDormId(id);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("无效的学生ID: " + id));
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        Long dormId = (long) student.getDorm().getId();
        studentRepository.deleteById(id);
        dormService.updateAvailableBeds(dormId, 1); // 释放床位
    }
    public long countStudents() {
        return studentRepository.countStudents();
    }
}