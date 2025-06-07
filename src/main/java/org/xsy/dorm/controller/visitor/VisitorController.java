package org.xsy.dorm.controller.visitor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.model.student.Student;
import org.xsy.dorm.model.visitor.Visitor;
import org.xsy.dorm.model.visitorPermission.VisitorPermissionResult;
import org.xsy.dorm.repository.VisitorRepository;
import org.xsy.dorm.service.VisitorService;

@Controller
@RequestMapping("/visitor")
@RequiredArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;

    @GetMapping
    public String listVisitor(Model model) {
        model.addAttribute("title","访客管理");
        model.addAttribute("activePage","visitor");
        model.addAttribute("visitor",visitorService.getAllVisitorList());
        return "visitor/list";
    }

    @GetMapping("/add")
    public String showAddVisitor(Model model) {
        model.addAttribute("title","添加访客");
        model.addAttribute("activePage","visitor");
        model.addAttribute("visitor", new Visitor());
        return "visitor/add";
    }

    @PostMapping("/add")
    public String approveVisitor(@ModelAttribute("visitor") Visitor visitor, Model model) {
        visitorService.saveVisitor(visitor);
        return "redirect:/visitor";
    }

    @GetMapping("/approve/{id}")
    public String approveVisitor(@PathVariable Long id, Model model) {
        Visitor visitor = visitorService.getVisitorById(id);
        model.addAttribute("title","审批访客");
        model.addAttribute("activePage","visitor");
        model.addAttribute("visitor",visitor);
        return "visitor/approve";
    }

    @PostMapping("/approve/{id}")
    public String processApproval(@PathVariable Long id,
                                  @RequestParam Student studentId,
                                  @RequestParam Admin adminId) throws IllegalAccessException {
        Student student = new Student();
        student.setId(studentId.getId());

        Admin admin = new Admin();
        admin.setId(adminId.getId());

        //调用服务层审批逻辑
        VisitorPermissionResult result = visitorService.checkVisitorPermission(studentId,adminId);
        result.setAdminId(adminId);
        result.setStudentId(student.getId());

        //根据审批结果更新访客状态
        visitorService.updateVisitorStatus(id,result.getStatus());{
            return "redirect:/visitor";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteVisitor(@PathVariable Long id, Model model) {
        visitorService.deleteVisitor(id);
        return "redirect:/visitor";
    }

    @GetMapping("/search")
    public String searchVisitor(@RequestParam(required = false)String keyWord ,Model model) {
        model.addAttribute("title","搜索访客");
        model.addAttribute("activePage","visitor");
        model.addAttribute("visitor",visitorService.searchVisitor(keyWord));
        return "visitor/list";
    }



}
