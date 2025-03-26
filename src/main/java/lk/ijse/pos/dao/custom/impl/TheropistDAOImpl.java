package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.config.FactoryConfiguration;
import lk.ijse.pos.dao.custom.TheropistDAO;
import lk.ijse.pos.entity.Theropist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class TheropistDAOImpl implements TheropistDAO {

    FactoryConfiguration factory;
    private Session session;

    public TheropistDAOImpl() throws IOException {
        factory = FactoryConfiguration.getInstance();
    }

    @Override
    public List<Theropist> getAll() {
        session = factory.getSession();
        List<Theropist> theropists = session.createQuery("from Theropist").list();
        session.close();
        return theropists;
    }

    @Override
    public boolean save(Theropist entity) {
        try{
            Session session = factory.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Theropist find(String id) {
        session = factory.getSession();
        Theropist theropist = session.get(Theropist.class, id);
        return theropist;
    }

    @Override
    public boolean update(Theropist entity) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Theropist existingTheropist = session.get(Theropist.class, entity.getTheropistId());

            if (existingTheropist != null) {
                existingTheropist.setName(entity.getName());
                existingTheropist.setContact(entity.getContact());
                existingTheropist.setEmail(entity.getEmail());
                existingTheropist.setStatus(entity.getStatus());

                session.update(existingTheropist);
                transaction.commit();
                return true;
            } else {
                System.out.println("theropist not found!");
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("theropist update failed: " + e.getMessage());
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String id) {
        Session session = factory.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Theropist theropist = session.get(Theropist.class, id);
            if (theropist != null) {
                session.delete(theropist);
                transaction.commit();
                System.out.println("theropist deleted successfully!");
            } else {
                System.out.println("theropist not found!");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("theropist deletion failed: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String generateNewID() {
        try (
               Session session = factory.getSession()) {
            String hql = "SELECT t.id FROM Theropist t ORDER BY t.id DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            String lastID = query.uniqueResult();

            if (lastID != null) {
                int newIndex = Integer.parseInt(lastID.substring(1)) + 1;
                return String.format("T%03d", newIndex);
            }
            return "T001";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
