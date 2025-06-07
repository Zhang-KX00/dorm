package org.xsy.dorm.model.repair;

import jakarta.persistence.*;
import lombok.Data;
import org.xsy.dorm.model.dorm.Dorm;
import org.xsy.dorm.model.student.Student;

import java.util.Date;

@Data
@Entity
@Table(name = "repair") //报修记录表
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "dorm_id")
    private Dorm DormId;

    @Enumerated(EnumType.STRING)
    private RepairType Type;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private RepairStatus status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "finish_time")
    private Date finishTime;

    @Column(name = "repair")
    private String repair;

    public Repair(
            Integer id,
            Student studentId,
            Dorm dormId,
            RepairType type,
            String description,
            RepairStatus status,
            Date createTime,
            Date finishTime,
            String repair) {

        this.id = id;
        this.studentId = studentId;
        DormId = dormId;
        Type = type;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.repair = repair;
    }

    public Repair() {

    }
}
