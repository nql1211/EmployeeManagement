package com.seatech.employeemanagement.controller;

import com.seatech.employeemanagement.model.Employee;
import com.seatech.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employee")
    public String list(Model model){
        model.addAttribute("employees", employeeService.findAllEmployee());
        return "list";
    }

    @GetMapping("employee/search")
    public String search(@RequestParam("term") String term, Model model){
        if (StringUtils.isEmpty(term)){
            return "redirect:/employee";
        }
        model.addAttribute("employees", employeeService.search(term));
        return "list";
    }

    @GetMapping("employee/add")
    public String addEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "form";
    }

    @GetMapping("employee/{id}/edit")
    public String editEmploy(@PathVariable("id") Long id, Model model){
        model.addAttribute("employee", employeeService.findById(id));
        return "form";
    }


    @PostMapping("employee/save")
    public String save(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            return "form";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("employee/{id}/delete")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirect){
        employeeService.deleteEmployee(id);
        redirect.addFlashAttribute("successMessage", "Deleted contact successfully!");
        return "redirect:/employee";
    }
}
