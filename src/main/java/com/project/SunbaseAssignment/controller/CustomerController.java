package com.project.SunbaseAssignment.controller;

import com.project.SunbaseAssignment.constant.CustomerConstant;
import com.project.SunbaseAssignment.entity.Customer;
import com.project.SunbaseAssignment.service.CustomerService;
import com.project.SunbaseAssignment.utils.CustomerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            return customerService.createCustomer(customer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CustomerUtil.getResponseEntity(CustomerConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("get_customer_list")
    public List<Customer> getCustomerList() {
        return customerService.getCustomerList();
    }

    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity<String> delete(@PathVariable Long customer_id) {
        try {
            return customerService.delete(customer_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CustomerUtil.getResponseEntity(CustomerConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update/{customer_id}")
    public ResponseEntity<String> update(@RequestBody Customer customer, @PathVariable Long customer_id) {
        try {
            return customerService.update(customer, customer_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CustomerUtil.getResponseEntity(CustomerConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
