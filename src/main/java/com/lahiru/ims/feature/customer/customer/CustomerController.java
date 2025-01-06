package com.lahiru.ims.feature.customer.customer;

import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.customer.customer.dto.CustomerRequestDto;
import com.lahiru.ims.feature.customer.customer.dto.CustomerResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.customer}")
@RequiredArgsConstructor
public class CustomerController implements GenericController<CustomerRequestDto, CustomerResponseDto> {
    public static final String CUSTOMER = "Customer";
    private final CustomerService customerService;

    @Override
    public ResponseEntity<PaginationResponse<CustomerResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<CustomerResponseDto> byPageWise = customerService.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(byPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<CustomerResponseDto>>> getAll() throws Exception {
        List<CustomerResponseDto> all = customerService.findAll();
        return ResponseEntityManager.ok(all);
    }

    @Override
    public ResponseEntity<StandardReponse<CustomerResponseDto>> createOne(CustomerRequestDto customerRequestDto) throws Exception {
        CustomerResponseDto customer = customerService.createOne(customerRequestDto);
        return ResponseEntityManager.created(customer, CUSTOMER);
    }

    @Override
    public ResponseEntity<StandardReponse<CustomerResponseDto>> updateOne(int id, CustomerRequestDto customerRequestDto) throws Exception {
        CustomerResponseDto customerResponseDto = customerService.updateOne(id, customerRequestDto);
        return ResponseEntityManager.updated(customerResponseDto, CUSTOMER);
    }

    @Override
    public ResponseEntity<StandardReponse<CustomerResponseDto>> deleteOne(int id) throws Exception {
        CustomerResponseDto customerResponseDto = customerService.deleteOne(id);
        return ResponseEntityManager.deleted(customerResponseDto, CUSTOMER);
    }

    @GetMapping("/search")
    public ResponseEntity<StandardReponse<List<CustomerResponseDto>>> searchCustomers(@RequestParam("key") String key) throws Exception {
        List<CustomerResponseDto> customersList = customerService.searchItem(key);
        return ResponseEntityManager.ok(customersList);
    }
}
