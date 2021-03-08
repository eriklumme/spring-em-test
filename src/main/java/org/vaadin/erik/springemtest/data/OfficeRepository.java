package org.vaadin.erik.springemtest.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author erik@vaadin.com
 * @since 3/5/21
 */
@Repository
public interface OfficeRepository extends JpaRepository<Office, Integer> {

    List<Office> findAll();
}
