package com.lahiru.ims.feature.inventory.employee;

import com.lahiru.ims.common.controller.GenericBasicInfoController;
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
public class EmployeeController implements GenericBasicInfoController<EmployeeDto> {
    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<StandardReponse<List<EmployeeDto>>> getAll() throws Exception {
        List<EmployeeDto> all = employeeService.getAll();
        return ResponseEntityManager.ok(all);
    }

    @Override
    public ResponseEntity<StandardReponse<List<EmployeeDto>>> searchByName(String key) throws Exception {
        List<EmployeeDto> employeeDtoList = employeeService.searchItem(key);
        return null;
    }
}
