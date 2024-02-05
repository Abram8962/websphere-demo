package com.ltim.testproject.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltim.testproject.dto.CustomerDTO;
import com.ltim.testproject.entity.Customer;

@Component
public class ConverterUtil {

	private ModelMapper modelMapper;

	@Autowired
	public ConverterUtil(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public CustomerDTO convertToDto(Customer customer) {
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);		
		return customerDTO;
	}

	public Customer convertToEntity(CustomerDTO customerDTO) throws ParseException {
		Customer customer = modelMapper.map(customerDTO, Customer.class);		
		return customer;
	}
}
