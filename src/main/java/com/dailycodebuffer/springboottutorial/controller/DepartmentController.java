package com.dailycodebuffer.springboottutorial.controller;

import com.dailycodebuffer.springboottutorial.entity.Department;
import com.dailycodebuffer.springboottutorial.error.DepartmentNotFoundException;
import com.dailycodebuffer.springboottutorial.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/save-department")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info("Inside Save Department");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/fetch-departments")
    public List<Department> fetchDepartmentList() {
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/fetch-department/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/delete-department/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        if (departmentService.deleteDepartmentById(departmentId))
            return "Department Deleted Successfully";
        else
            return "Error While Deleting Department.";

    }

    @PutMapping("/update-department/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department) {
        return departmentService.updateDepartment(departmentId, department);
    }


    @GetMapping("/department/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.fetchDepartmentByName(departmentName);
    }


}
