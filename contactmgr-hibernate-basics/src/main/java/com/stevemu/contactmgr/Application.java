package com.stevemu.contactmgr;

import com.stevemu.contactmgr.model.Contact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import java.util.List;

public class Application {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact  = new Contact.ContactBuilder("a", "b")
                .withEmail("ssdf")
                .withPhone((long) 234234231)
                .build();

//        System.out.println(contact);

//        int id = save(contact);

//        for (Contact c: fetchAllContacts()) {
//            System.out.println(c);
//        }

//        System.out.println("before");
//        fetchAllContacts().stream().forEach(System.out::println);
//
        Contact c = findContactById(1);
//
//        c.setFirstName("beatrix");
//
//        update(c);
//
//        System.out.println("after update");


        delete(c);
        fetchAllContacts().stream().forEach(System.out::println);



    }

    private static Contact findContactById(int id) {
        Session session = sessionFactory.openSession();
        Contact contact = session.get(Contact.class, id);
        session.close();
        return contact;
    }

    private static void update(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(contact);
        session.getTransaction().commit();
        session.close();
    }

    private static void delete(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(contact);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    private static List<Contact> fetchAllContacts() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Contact.class);
        List<Contact> contacts = criteria.list();
        session.close();
        return contacts;
    }

    private static int save(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int id = (int)session.save(contact);
        session.getTransaction().commit();
        session.close();
        return id;
    }


}
