package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

public class ClientDemo 
{

public static void main(String[] args) 
{

Configuration cfg = new Configuration().configure();
SessionFactory sf = cfg.buildSessionFactory();
Session session = sf.openSession();

Transaction tx = session.beginTransaction();

/* INSERT RECORD */

Shipment s = new Shipment();

s.setName("Electronics Shipment");
s.setDate(new Date());
s.setStatus("Dispatched");
s.setWeight(120.5);

session.save(s);

tx.commit();

System.out.println("Shipment Inserted");


/* DELETE USING HQL */

Transaction tx2 = session.beginTransaction();

String hql = "delete from Shipment where id=:sid";

Query<?> q = session.createQuery(hql);
q.setParameter("sid",1);

int result = q.executeUpdate();

System.out.println(result + " Shipment Deleted");

tx2.commit();

session.close();
sf.close();

}

}