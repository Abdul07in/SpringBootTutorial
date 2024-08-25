package com.dailycodebuffer.springboottutorial.service;

import com.dailycodebuffer.springboottutorial.entity.Department;
import com.dailycodebuffer.springboottutorial.error.DepartmentNotFoundException;
import com.dailycodebuffer.springboottutorial.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Found");
        }

        return department.get();
    }

    @Override
    public Boolean deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
        return true;
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depObj = departmentRepository.findById(departmentId).orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + departmentId));

        if (Objects.nonNull(department.getDepartmentName()) && !department.getDepartmentName().trim().isEmpty()) {
            depObj.setDepartmentName(department.getDepartmentName().trim());
        }
        if (Objects.nonNull(department.getDepartmentAddress()) && !department.getDepartmentAddress().trim().isEmpty()) {
            depObj.setDepartmentAddress(department.getDepartmentAddress().trim());
        }
        if (Objects.nonNull(department.getDepartmentCode()) && !department.getDepartmentCode().trim().isEmpty()) {
            depObj.setDepartmentCode(department.getDepartmentCode().trim());
        }

        return departmentRepository.save(depObj);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }


}
