package org.xsy.dorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.model.student.Student;
import org.xsy.dorm.model.visitor.Visitor;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByStudentId(Student studentId); //通过学生id查询访问记录
    List<Visitor> findByAdminId(Admin adminId);//通过管理员id进行审批

    List<Visitor> findByNameContainingOrPhoneContainingOrReasonContaining(String keyword, String keyword1, String keyword2);
}
