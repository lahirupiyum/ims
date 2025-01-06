package com.lahiru.ims.feature.customer.customer;

import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.GenericSearchService;
import com.lahiru.ims.common.service.GenericService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.feature.customer.customer.dto.CustomerRequestDto;
import com.lahiru.ims.feature.customer.customer.dto.CustomerResponseDto;

public interface CustomerService extends
        GenericService<CustomerRequestDto, CustomerResponseDto>,
        EntityFinderService<Customer>,
        ModelMapperService<Customer, CustomerRequestDto, CustomerResponseDto>,
        GenericSearchService<CustomerResponseDto> {
}
