package org.vaadin.erik.springemtest;

import java.util.Collections;

import javax.transaction.Transactional;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vaadin.erik.springemtest.data.Employee;
import org.vaadin.erik.springemtest.data.Office;
import org.vaadin.erik.springemtest.data.OfficeRepository;
import org.vaadin.erik.springemtest.data.OfficeService;

/**
 * @author erik@vaadin.com
 * @since 3/5/21
 */
@SpringBootTest
class PersistenceContextTest {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OfficeService officeService;

    @BeforeEach
    void setUp() {
        Employee employee = new Employee();
        employee.setFullName("I. AM. Employee");

        Office office = new Office();
        office.setOfficeName("Third floor");
        office.setEmployees(Collections.singletonList(employee));

        employee.setOffice(office);

        officeRepository.saveAndFlush(office);
    }

    @Test
    void findAllFromRepository_accessWithoutTransaction_lazyInitException() {
        Office office = officeRepository.findAll().get(0);
        Assertions.assertThrows(
                LazyInitializationException.class,
                () -> getEmployee(office)
        );
    }

    @Test
    void findAllFromService_accessWithoutTransaction_lazyInitException() {
        Office office = officeService.findAll().get(0);
        Assertions.assertThrows(
                LazyInitializationException.class,
                () -> getEmployee(office)
        );
    }

    @Test
    @Transactional
    void findAllFromRepository_accessWithTransaction_noException() {
        Office office = officeRepository.findAll().get(0);
        getEmployee(office);
    }

    @Test
    @Transactional
    void findAllFromService_accessWithTransaction_noException() {
        Office office = officeRepository.findAll().get(0);
        getEmployee(office);
    }

    @Test
    @Transactional
    void findAllWithTransactionFromService_accessWithTransaction_noException() {
        Office office = officeService.findAllWithTransactional().get(0);
        getEmployee(office);
    }

    @Test
    void findAllWithTransactionRequiresNewFromService_accessWithTransaction_lazyInitException() {
        Office office = officeService.findAllWithTransactionalRequiresNew().get(0);
        Assertions.assertThrows(
                LazyInitializationException.class,
                () -> getEmployee(office)
        );
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void getEmployee(Office office) {
        office.getEmployees().get(0);
    }
}
