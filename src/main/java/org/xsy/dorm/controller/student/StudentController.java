package org.xsy.dorm.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xsy.dorm.model.student.Student;
import org.xsy.dorm.service.DormService;
import org.xsy.dorm.service.StudentService;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final DormService dormService;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("activePage", "student");
        return "students/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("dorms", dormService.getAvailableDorms());
        return "students/create";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student, @RequestParam Long dormId) {
        studentService.saveStudent(student, dormId);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("dorms", dormService.getAvailableDorms());
        return "students/edit";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student,
                                @RequestParam Long dormId) {
        student.setId(id);
        studentService.saveStudent(student, dormId);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}