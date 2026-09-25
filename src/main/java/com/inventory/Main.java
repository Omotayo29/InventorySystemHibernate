package com.inventory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    static void main() {
        Product product = new Product();
        product.setName("Wireless Mouse");
        product.setPrice(15.99);
        product.setQuantity(50);

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(com.inventory.Product.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product a = session.find(Product.class,1);
        a.setQuantity(39);
       session.remove(a);
        transaction.commit();
        sessionFactory.close();
        session.close();


    }
}
