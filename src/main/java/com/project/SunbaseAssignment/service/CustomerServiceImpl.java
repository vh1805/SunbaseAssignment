package com.project.SunbaseAssignment.service;

import com.project.SunbaseAssignment.constant.CustomerConstant;
import com.project.SunbaseAssignment.entity.Customer;
import com.project.SunbaseAssignment.repository.CustomerRepository;
import com.project.SunbaseAssignment.utils.CustomerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<String> createCustomer(Customer customer) {
        try {
            if (isCustomerValid(customer) == true) {
                customerRepository.save(customer);
                return CustomerUtil.getResponseEntity(CustomerConstant.SUCCESSFULLY_CREATED, HttpStatus.OK);
            } else {
                return CustomerUtil.getResponseEntity(CustomerConstant.FIRST_NAME_OR_LAST_NAME_IS_MISSING, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CustomerUtil.getResponseEntity(CustomerConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<Customer> getCustomerList() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public ResponseEntity<String> delete(Long customerId) {
        try {
            boolean isCustomerExist = false;
            List<Customer> customers = customerRepository.findAll();

            for (Customer c : customers) {
                if (c.getCustomerId() == customerId) {
                    isCustomerExist = true;
                }
            }
            if (isCustomerExist == true) {
                customerRepository.deleteById(customerId);
                return CustomerUtil.getResponseEntity(CustomerConstant.SUCCESSFULLY_DELETED, HttpStatus.OK);
            } else {
                return CustomerUtil.getResponseEntity(CustomerConstant.UUID_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CustomerUtil.getResponseEntity(CustomerConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Customer customer, Long customerId) {
        try {
            boolean isCustomerExist = false;
            List<Customer> customers = customerRepository.findAll();

            for (Customer c : customers) {
                if (c.getCustomerId() == customerId) {
                    isCustomerExist = true;
                }
            }
            if (isCustomerExist == true) {
                Customer customerDb = customerRepository.getById(customerId);
                if (customer.getFirstName() != null && customer.getFirstName().trim() != "") {
                    customerDb.setFirstName(customer.getFirstName());
                }
                if (customer.getLastName() != null && customer.getLastName().trim() != "") {
                    customerDb.setLastName(customer.getLastName());
                }
                if (customer.getStreet() != null && customer.getStreet().trim() != "") {
                    customerDb.setStreet(customer.getStreet());
                }
                if (customer.getAddress() != null && customer.getAddress().trim() != "") {
                    customerDb.setAddress(customer.getAddress());
                }
                if (customer.getCity() != null && customer.getCity().trim() != "") {
                    customerDb.setCity(customer.getCity());
                }
                if (customer.getState() != null && customer.getState().trim() != "") {
                    customerDb.setState(customer.getState());
                }
                if (customer.getEmail() != null && customer.getEmail().trim() != "") {
                    customerDb.setEmail(customer.getEmail());
                }
                if (customer.getPhone() != null && customer.getPhone().trim() != "") {
                    customerDb.setPhone(customer.getPhone());
                }
                customerRepository.save(customerDb);
                return CustomerUtil.getResponseEntity(CustomerConstant.SUCCESSFULLY_UPDATED, HttpStatus.OK);
            } else {
                return CustomerUtil.getResponseEntity(CustomerConstant.UUID_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CustomerUtil.getResponseEntity(CustomerConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean isCustomerValid(Customer customer) {
        if (customer.getFirstName().trim() == "" || customer.getFirstName() == null
                || customer.getLastName().trim() == "" || customer.getLastName() == null) {
            return false;
        }
        return true;
    }
}
