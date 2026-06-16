package org.example;

import exception.CustomerExistsException;
import exception.CustomerNotFoundException;
import model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {
    private CustomerService customerService;
    private List<Customer> customers;

    @BeforeEach
    public void setUp() {
        customers = new ArrayList<>(List.of(
                Customer.builder()
                        .id(1)
                        .name("Koushik")
                        .email("Koushik@gmail.com")
                        .balance(100)
                        .build(),

                Customer.builder()
                        .id(2)
                        .name("Malli")
                        .email("malli@gmail.com")
                        .balance(200)
                        .build()
        ));

        customerService = new CustomerService(customers);
    }

    @Test
    void shouldAddCustomerWhenDataIsValid() {
        Customer customer =
                new Customer(3, "Prasana", "Prasana@test.com", 500.0);

        Customer result = customerService.addCustomer(customer);

        assertEquals("Prasana", result.getName());
        assertEquals(3, customerService.getAllCustomers().size());
    }

    @Test
    void shouldThrowExceptionWhenAddingDuplicateCustomer() {
        Customer duplicate =
                new Customer(1, "Test", "test@test.com", 500.0);

        assertThrows(CustomerExistsException.class,
                () -> customerService.addCustomer(duplicate));
    }

    @Test
    void shouldReturnCustomerWhenValidId() {
        Customer customer = customerService.getCustomerById(1);

        assertEquals("Koushik", customer.getName());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        assertThrows(CustomerNotFoundException.class,
                () -> customerService.getCustomerById(100));
    }

    @Test
    void shouldDeleteCustomerWhenValidId() {
        boolean result = customerService.deleteCustomer(1);

        assertTrue(result);
        assertEquals(1, customerService.getAllCustomers().size());
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingCustomer() {
        assertThrows(CustomerNotFoundException.class,
                () -> customerService.deleteCustomer(999));
    }

    @Test
    void shouldReturnAllCustomers() {
        assertEquals(2, customerService.getAllCustomers().size());
    }

    @Test
    void shouldReturnTotalBalance() {
        double total = customerService.getTotalBalance();

        assertEquals(300.0, total);
    }

    @AfterEach
    void tearDown() {
        customerService = null;
        customers = null;
    }
}
