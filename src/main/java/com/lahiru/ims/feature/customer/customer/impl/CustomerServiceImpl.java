package com.lahiru.ims.feature.customer.customer.impl;

import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.customer.Customer;
import com.lahiru.ims.feature.customer.customer.CustomerRepo;
import com.lahiru.ims.feature.customer.customer.CustomerService;
import com.lahiru.ims.feature.customer.customer.dto.CustomerRequestDto;
import com.lahiru.ims.feature.customer.customer.dto.CustomerResponseDto;
import com.lahiru.ims.feature.inventory.employee.Employee;
import com.lahiru.ims.feature.inventory.employee.EmployeeService;
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
    public static final String CUSTOMER = "Customer";
    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;
    private final GenericDao genericDao;
    private final EmployeeService employeeService;

    @Override
    public PaginationResponse<CustomerResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> allByPageWise = customerRepo.findAllByPageWise(pageable);
        List<CustomerResponseDto> customerResponseDtoList = allByPageWise.map(this::convertToDto).stream().toList();
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

        Employee accountManager = checkAndCreateAccountManger(customerRequestDto.getAccountManager());
        Customer customer = convertToModel(customerRequestDto);
        customer.setAccountManager(accountManager);
        Customer savedCustomer = customerRepo.save(customer);
        return convertToDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto updateOne(int id, CustomerRequestDto customerRequestDto) throws Exception {
        Customer fetchedCustomer = customerRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(CUSTOMER));
        boolean isEmailValid = Objects.equals(customerRequestDto.getEmail(), fetchedCustomer.getEmail()) || !customerRepo.isActiveByEmail(customerRequestDto.getEmail());
        if (!isEmailValid) throw new DataConflictException("Customer email");

        Employee accountManger = checkAndCreateAccountManger(customerRequestDto.getAccountManager());
        Customer customer = convertToModel(customerRequestDto);
        customer.setId(id);
        customer.setAccountManager(accountManger);
        Customer updatedCustomer = customerRepo.save(customer);
        return convertToDto(updatedCustomer);
    }

    @Override
    public CustomerResponseDto deleteOne(int id) throws Exception {
        Customer customer = customerRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(CUSTOMER));
        customer.setIsActive(false);
        Customer savedCustomer = customerRepo.save(customer);

        return convertToDto(savedCustomer);
    }

    @Override
    public Customer convertToModel(CustomerRequestDto customerRequestDto) {
        modelMapper.typeMap(CustomerRequestDto.class, Customer.class).addMappings(mapper -> {
            mapper.skip(Customer::setAccountManager);
        });
        Customer customer = modelMapper.map(customerRequestDto, Customer.class);
        customer.setIsActive(true);
        return customer;
    }

    @Override
    public CustomerResponseDto convertToDto(Customer customer) {
        modelMapper.typeMap(Customer.class, CustomerResponseDto.class).addMappings(mapper -> {
            mapper.with(req -> new EmployeeDto()).map(Customer::getAccountManager, CustomerResponseDto::setAccountManager);
        });

        return modelMapper.map(customer, CustomerResponseDto.class);
    }

    private Employee checkAndCreateAccountManger(EmployeeDto employeeDto) throws Exception {
        return genericDao.checkAndCreate(employeeDto, employeeService);
    }
}
