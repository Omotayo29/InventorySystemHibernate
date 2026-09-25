package com.inventory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
     void main() {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(com.inventory.Product.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        create(sessionFactory);
        read(sessionFactory,2);
        update(sessionFactory,2);
        delete(sessionFactory,2);
        sessionFactory.close();
    }

    public void create(SessionFactory sf){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        Product product1 = new Product("Wireless Mouse",20,12);
        Product product2 = new Product("Flash Drive",30,30);
        Product product3 = new Product("Monitor",40,52);

        session.persist(product1);
        session.persist(product2);
        session.persist(product3);

        transaction.commit();
        session.close();
    }

    public void read(SessionFactory sf, int id){
        Session session = sf.openSession();
        Product product = session.find(Product.class,id);
        System.out.println(product);
        session.close();
    }

    public void update(SessionFactory sf, int id){
        Scanner scanner = new Scanner(System.in);
        Session session = sf.openSession();
        Product product;
        Transaction transaction = session.beginTransaction();
        System.out.print(
                "What would you like to update: \n1. Quantity\n 2. Price\n "
        );
        int input = scanner.nextInt();
        if(input==1){
            System.out.print("New Quantity: ");
            input = scanner.nextInt();
            product = session.find(Product.class,id);
            product.setQuantity(input);
        }
        else if(input==2){
            System.out.print("New Price: ");
            double inp = scanner.nextDouble();
            product = session.find(Product.class,id);
            product.setPrice(inp);
        }
        else{
            System.out.println("Invalid input.");
        }
        transaction.commit();
        session.close();
        scanner.close();
        System.out.println("Successful");
    }

    public void delete(SessionFactory sf, int id){
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.find(Product.class,id);

        if(product ==null) {
            System.out.println("No such id");
            return;
        }
        session.remove(product);
        transaction.commit();
        session.close();
        System.out.println("Deleted");
    }
}
