package com.ltim.testproject.service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ltim.testproject.dto.CustomerDTO;
import com.ltim.testproject.dto.CustomerResponse;
import com.ltim.testproject.entity.Customer;
import com.ltim.testproject.repository.CustomerRepository;
import com.ltim.testproject.util.ConverterUtil;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	private ConverterUtil converterUtil;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, ConverterUtil converterUtil) {
		this.customerRepository = customerRepository;
		this.converterUtil = converterUtil;
	}

	public CustomerService() {
		super();
	}

	public CustomerDTO findById(long id) {
		Customer customer = customerRepository.findById(id).get();
		return converterUtil.convertToDto(customer);

	}

	public CustomerResponse retrieveCustomers() {
		CustomerResponse response = new CustomerResponse();
		List<CustomerDTO> customerList = customerRepository.findAll().stream().map(c -> converterUtil.convertToDto(c))
				.collect(Collectors.toList());

		response.setCustomerList(customerList);
		return response;
	}

	public CustomerDTO addCustomer(CustomerDTO customerDTO) throws ParseException {
		Customer customer = converterUtil.convertToEntity(customerDTO);
		customer = customerRepository.save(customer);
		return converterUtil.convertToDto(customer);
	}

	public CustomerDTO updateCustomer(long id, CustomerDTO customerDTO) throws ParseException {
		Customer customer = converterUtil.convertToEntity(customerDTO);
		customer.setId(id);
		customer = customerRepository.save(customer);
		return converterUtil.convertToDto(customer);
	}

	public Boolean deleteCustomer(long id) {
		Boolean responseFlag ;
		Customer customer = new Customer();
		customer.setId(id);
		try {
			customerRepository.delete(customer);
			responseFlag = true;
		} catch (Exception e) {
			responseFlag = false;
		}
		return responseFlag;
	}

}