package org.xsy.dorm.model.visitor;

import jakarta.persistence.*;
import lombok.Data;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.model.student.Student;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "visitor") //访客登记表
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;

    @Column(name = "visitor_name")
    private String visitorName;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "contact")
    private String contact;

    @Column(name = "visit_reason")
    private String visitReason;

    @Column(name = "visit_time")
    private LocalDateTime visitTime;

    @Column(name = "leave_time")
    private LocalDateTime leaveTime;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin adminId;

    private String name;
    private String phone;
    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status;

}
