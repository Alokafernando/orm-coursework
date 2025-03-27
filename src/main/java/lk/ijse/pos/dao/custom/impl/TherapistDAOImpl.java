package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.config.FactoryConfiguration;
import lk.ijse.pos.dao.custom.TherapistDAO;
import lk.ijse.pos.entity.Therapist;
import lk.ijse.pos.tm.TherapistTM;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {

    FactoryConfiguration factory;
    private Session session;

    public TherapistDAOImpl() throws IOException {
        factory = FactoryConfiguration.getInstance();
    }

    @Override
    public List<Therapist> getAll() {
        session = factory.getSession();
        List<Therapist> therapists = session.createQuery("from Therapist").list();
        session.close();
        return therapists;
    }

    @Override
    public boolean save(Therapist entity) {
        Session session = factory.getSession();
        try{

            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if (session!=null) session.close();
        }

    }

    @Override
    public Therapist find(String text) {
        return null;
    }

    @Override
    public TherapistTM findTheropist(String id) {
        session = factory.getSession();
        TherapistTM therapist = session.get(TherapistTM.class, id);
        return therapist;
    }

    @Override
    public boolean update(Therapist entity) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Therapist existingTherapist = session.get(Therapist.class, entity.getTherapistId());

            if (existingTherapist != null) {
                existingTherapist.setName(entity.getName());
                existingTherapist.setContact(entity.getContact());
                existingTherapist.setEmail(entity.getEmail());
                existingTherapist.setStatus(entity.getStatus());

                session.update(existingTherapist);
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

            Therapist theropist = session.get(Therapist.class, id);
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
            String hql = "SELECT t.id FROM Therapist t ORDER BY t.id DESC";
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
