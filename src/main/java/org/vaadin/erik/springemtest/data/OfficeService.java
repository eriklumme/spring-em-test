package org.vaadin.erik.springemtest.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

/**
 * @author erik@vaadin.com
 * @since 3/5/21
 */
@Service
public class OfficeService {

    @PersistenceContext
    private EntityManager em;

    public List<Office> findAll() {
        return em.createQuery("FROM Office o", Office.class).getResultList();
    }

    @Transactional
    public List<Office> findAllWithTransactional() {
        return findAll();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<Office> findAllWithTransactionalRequiresNew() {
        return findAll();
    }
}
