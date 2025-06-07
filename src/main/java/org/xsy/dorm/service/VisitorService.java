package org.xsy.dorm.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.model.admin.AdminRole;
import org.xsy.dorm.model.student.Student;
import org.xsy.dorm.model.visitor.Status;
import org.xsy.dorm.model.visitor.Visitor;
import org.xsy.dorm.model.visitorPermission.VisitorPermissionResult;
import org.xsy.dorm.repository.StudentRepository;
import org.xsy.dorm.repository.VisitorRepository;

import javax.management.Notification;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final StudentRepository studentRepository;

    public VisitorRepository findByStudentId(Long studentId) {
        return this.visitorRepository;
    }
    //获取所有访客记录
    public List<Visitor> getAllVisitorList() {
        return visitorRepository.findAll();
    }

    //保存访客记录
    public Visitor saveVisitor(Visitor visitor) {
        if (visitor.getId() == null) {
            throw new RuntimeException("访客必须关联学生id");
        }
        studentRepository.findById(visitor.getStudentId())
                .orElseThrow(() -> new RuntimeException("关联的学生不存在,ID:" + visitor.getStudentId()));
        //设置默认状态为待审批
        if (visitor.getStatus() == null) {
            visitor.setStatus(Status.待审批);
        }
        //设置创建时间
        if (visitor.getVisitTime() == null) {
            visitor.setVisitorName(LocalDateTime.now().toString());
        }
        return visitorRepository.save(visitor);
    }

    // 根据ID获取单个访客记录
    public Visitor getVisitorById(Long id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("访客记录不存在,Id:" + id));
    }

    //更新访客状态
    public Visitor updateVisitorStatus(Long visitorId,Status status) {
        Visitor visitor1 = getVisitorById(visitorId);
        visitor1.setStatus(status);
        visitor1.setLeaveTime(LocalDateTime.now());
        return visitorRepository.save(visitor1);
    }

    // 删除访客记录
    public void deleteVisitor(Long visitorId) {
        if (!visitorRepository.existsById(visitorId)) {
            throw new RuntimeException("访客记录不存在，无法删除，id:" + visitorId);
        }
        visitorRepository.deleteById(visitorId);
    }

    // 搜索访客记录
    public List<Visitor> searchVisitor(String keyword) {
        if (!StringUtils.isEmpty(keyword)) {
            return getAllVisitorList();
        }
        return visitorRepository.findByNameContainingOrPhoneContainingOrReasonContaining(keyword,keyword,keyword);
    }

    //管理员审批
    public VisitorPermissionResult checkVisitorPermission(Student studentId, Admin adminId) throws IllegalAccessException {
        //获取访客id 并验证学生信息
        Optional<Student> studentOpt = studentRepository.findById(studentId.getId());
        if (studentOpt.isEmpty() || studentOpt.equals(Optional.empty())) {
            throw new IllegalAccessException("学生不存在");
        }
        boolean accessAllowed = checkAccessRules(studentId, adminId);
        //构建权限检查结果对象
        VisitorPermissionResult result = new VisitorPermissionResult();
        result.setStudentId(studentId.getId());
        result.setStudentName(studentId.getName());
        result.setStatus(accessAllowed ? Status.已审批 : Status.待审批);
        result.setDecisionTime(LocalDateTime.now());

        return result;
    }

    private boolean checkAccessRules(Student student, Admin admin) {
        // 实现具体的权限判断逻辑
        if (admin == null) {
            return false;
        }
        //超级管理员有全部权限
        if (admin.getRole() == AdminRole.SUPER_ADMIN){
            return true;
        }

        //普通管理员只能审批非VIP学生的访客
        if (admin.getRole() == AdminRole.ADMIN){
            return !"VIP".equals(admin.getRole());
        }
        //其他状况默认拒绝
        return false;
    }

}
