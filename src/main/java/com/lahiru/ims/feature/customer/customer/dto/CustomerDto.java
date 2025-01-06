package com.lahiru.ims.feature.customer.customer.dto;

import com.lahiru.ims.feature.customer.customer.CustomerPriority;
import com.lahiru.ims.feature.inventory.employee.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class CustomerDto {
    private String name;
    private CustomerPriority priority;
    private String address;
    private String contactNo;
    private String email;
    private EmployeeDto accountManager;
    private String vsnlId;
    private String asNumber;
}
