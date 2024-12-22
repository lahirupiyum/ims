package com.lahiru.ims.feature.inventory.employee;

import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.inventory.employee.dto.EmployeeDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.employee}")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    ResponseEntity<StandardReponse<List<EmployeeDto>>> getAll() throws Exception {
        List<EmployeeDto> all = employeeService.getAll();
        return ResponseEntityManager.ok(all);
    }
}
