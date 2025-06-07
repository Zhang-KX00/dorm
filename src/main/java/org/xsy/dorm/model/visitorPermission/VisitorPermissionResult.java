package org.xsy.dorm.model.visitorPermission;

import jakarta.persistence.*;
import lombok.Data;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.model.student.Student;
import org.xsy.dorm.model.visitor.Status;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "visitor_permission_result")
public class VisitorPermissionResult  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "student_name")
    private Student studentName;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin adminId;

    @Column(name = "decision_time")
    private LocalDateTime decisionTime;

    @Column(name = "allowed")
    private Boolean allowed;

    @Column(name = "reason")
    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void setStudentId(Long id) {

    }

    public void setStudentName(String name) {
    }

    public void setApproved(boolean isApproved) {
    }

    public void setApproved(Status status) {

    }
}
