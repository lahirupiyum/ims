package com.lahiru.ims.feature.customer.customer.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.customer.Customer;
import com.lahiru.ims.feature.customer.customer.CustomerRepo;
import com.lahiru.ims.feature.customer.customer.CustomerService;
import com.lahiru.ims.feature.customer.customer.dto.CustomerRequestDto;
import com.lahiru.ims.feature.customer.customer.dto.CustomerResponseDto;
import com.lahiru.ims.feature.inventory.employee.Employee;
import com.lahiru.ims.feature.inventory.employee.dto.EmployeeDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;

    @Override
    public PaginationResponse<CustomerResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> allByPageWise = customerRepo.findAllByPageWise(pageable);
        List<CustomerResponseDto> customerResponseDtoList =  allByPageWise.map(this::convertToDto).stream().toList();
        int totalElements = (int) allByPageWise.getTotalElements();
        return new PaginationResponse<>(customerResponseDtoList, totalElements);
    }

    @Override
    public List<CustomerResponseDto> findAll() throws Exception {
        List<Customer> customerList = customerRepo.findAllActive();
        return customerList.stream().map(this::convertToDto).toList();
    }

    @Override
    public CustomerResponseDto createOne(CustomerRequestDto customerRequestDto) throws Exception {
        if (customerRepo.isActiveByEmail(customerRequestDto.getEmail()))
            throw new DataConflictException("Customer email");

        // TODO check and create new employee after merge

        Customer customer = convertToModel(customerRequestDto);
        Customer savedCustomer = customerRepo.save(customer);
        return convertToDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto updateOne(int id, CustomerRequestDto customerRequestDto) throws Exception {
        Customer customer = customerRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException("Customer"));
        boolean isEmailValid = Objects.equals(customerRequestDto.getEmail(), customer.getEmail()) || !customerRepo.isActiveByEmail(customerRequestDto.getEmail());
        if (!isEmailValid)
            throw new DataConflictException("Customer email");
        
        // TODO check and create new employee after merge

        Customer updatedCustomer = convertToModel(customerRequestDto);
        updatedCustomer.setId(id);
        updatedCustomer = customerRepo.save(updatedCustomer);
        return convertToDto(updatedCustomer);
    }

    @Override
    public CustomerResponseDto deleteOne(int id) throws Exception {
        Customer customer = customerRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException("Customer"));
        customer.setIsActive(false);
        Customer savedCustomer = customerRepo.save(customer);

        return convertToDto(savedCustomer);
    }

    @Override
    public Customer convertToModel(CustomerRequestDto customerRequestDto) {
        modelMapper.typeMap(CustomerRequestDto.class, Customer.class)
                .addMappings(mapper -> {
                    mapper.with(req -> new Employee()).map(CustomerRequestDto::getAccountManager, Customer::setAccountManager);
                });
        Customer customer = modelMapper.map(customerRequestDto, Customer.class);
        customer.setIsActive(true);
        return customer;
    }

    @Override
    public CustomerResponseDto convertToDto(Customer customer) {
        modelMapper.typeMap(Customer.class, CustomerResponseDto.class)
                .addMappings(mapper -> {
                    mapper.with(req -> new EmployeeDto()).map(Customer::getAccountManager, CustomerResponseDto::setAccountManager);
                });

        return modelMapper.map(customer, CustomerResponseDto.class);
    }
}
