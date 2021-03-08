package org.vaadin.erik.springemtest;

import javax.annotation.PostConstruct;

import org.springframework.boot.test.context.TestConfiguration;

/**
 * @author erik@vaadin.com
 * @since 3/5/21
 */
@TestConfiguration
public class PersistenceTestConfiguration {

    @PostConstruct
    void init() {

    }
}
