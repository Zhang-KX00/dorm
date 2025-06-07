package org.xsy.dorm.controller.dorm;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xsy.dorm.model.dorm.Dorm;
import org.xsy.dorm.service.DormService;
import org.xsy.dorm.service.StudentService;

@Controller
@RequestMapping("/dorms")
@RequiredArgsConstructor
public class DormController {
    private final DormService dormService;
    private final StudentService studentService;

    @GetMapping
    public String listDorms(Model model) {
        model.addAttribute("title", "宿舍管理");
        model.addAttribute("activePage", "dorm");
        model.addAttribute("dorms", dormService.getAllDorms());
        return "dorm/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("title", "添加宿舍");
        model.addAttribute("activePage", "dorm");
        model.addAttribute("dorm", new Dorm());
        return "dorm/create";
    }

    @GetMapping("/{id}/students")
    public String showDormStudents(@PathVariable Long id, Model model) {
        Dorm dorm = dormService.getDormById(id);
        model.addAttribute("title", dorm.getBuildingName() + "-" + dorm.getRoomNumber() + " 学生");
        model.addAttribute("activePage", "dorm");
        model.addAttribute("dorm", dorm);
        model.addAttribute("students", studentService.getStudentsByDorm(id));
        return "dorm/students";
    }
}