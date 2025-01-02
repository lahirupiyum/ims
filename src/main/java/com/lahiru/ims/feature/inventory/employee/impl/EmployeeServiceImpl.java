package com.lahiru.ims.feature.inventory.employee.impl;

import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.employee.Employee;
import com.lahiru.ims.feature.inventory.employee.EmployeeRepo;
import com.lahiru.ims.feature.inventory.employee.EmployeeService;
import com.lahiru.ims.feature.inventory.employee.dto.EmployeeDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;

    @Override
    public Employee createOne(String name) throws Exception {
        Employee employee = new Employee();
        employee.setName(name);
        return employeeRepo.save(employee);
    }

    @Override
    public List<EmployeeDto> getAll() throws Exception {
        List<Employee> allEmployees = employeeRepo.findAll();
        return allEmployees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).toList();
    }

    @Override
    public Employee findOne(Integer id) throws Exception {
        return employeeRepo.findById(id).orElseThrow(() -> new NotFoundException("Employee"));
    }

    @Override
    public List<EmployeeDto> searchItem(String searchKey) throws Exception {
        List<Employee> employees = employeeRepo.searchByName(searchKey);
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).toList();
    }
}
