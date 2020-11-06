package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class CRUDRepositoryImpl implements CRUDRepository {

    private SessionFactory sessionFactory;

    public CRUDRepositoryImpl() {

    }

    //Initializes a session factory
    void init() {
        if (Objects.nonNull(sessionFactory)) {
            return;
        }

        this.sessionFactory = new Configuration()
                .configure("com/company/hibernate.cfg.xml")
                .buildSessionFactory();

    }


    @Override
    public void create(Object o) {
        init();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.close();

    }

    @Override
    public Object read(Class tClass, Long id) {

        init();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Object o = session.get(tClass, id);
        session.close();
        return o;


    }

    @Override
    public void update(Long id, Object newObject, Class tClass) throws Exception {
        init();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Object oldObject = session.get(tClass, id);
        if (Objects.isNull(oldObject)) {
            throw new Exception("No record found to update");
        }
        session.merge(newObject);
        
        transaction.commit();
        
        session.close();


    }

    @Override
    public void delete(Long id, Object obj, Class tClass) throws Exception {
        init();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Object oldObject = session.get(tClass, id);
        if (Objects.isNull(oldObject)) {
            throw new Exception("No record found to update");
        }
        session.delete(obj);
        transaction.commit();

        session.close();


    }
}
