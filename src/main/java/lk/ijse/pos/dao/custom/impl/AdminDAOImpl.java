package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.config.FactoryConfiguration;
import lk.ijse.pos.dao.custom.AdminDAO;
import lk.ijse.pos.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    private static FactoryConfiguration factory;
    private Session session;

    public AdminDAOImpl() throws IOException {

            factory = FactoryConfiguration.getInstance();
    }

    @Override
    public List<Admin> getAll() {
        return List.of();
    }

    @Override
    public boolean save(Admin entity) {
        session = factory.getSession();  // Use the initialized session
        try {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Admin find(String name) {
        session = factory.getSession();
        Admin admin = session.get(Admin.class, name);
        return admin;
    }

    @Override
    public boolean update(Admin entity) {
        session = factory.getSession();
        try {
            Admin admin = session.get(Admin.class, entity);
            Transaction transaction = session.beginTransaction();

            admin.setUsername(entity.getUsername());
            admin.setPassword(entity.getPassword());

            transaction.commit();
            return admin!=null;
        } catch (Exception e) {
            System.out.println("Customer update failed");
            return false;
        }
    }

    @Override
    public void delete(String id) {
        // Implement the delete method here
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<String> getAdminNames() {
        session = factory.getSession();
        List<String> adminNames;
        try {
            Query<String> query = session.createQuery("SELECT a.username FROM Admin a", String.class);
            adminNames = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            adminNames = new ArrayList<>();
        } finally {
            session.close();
        }
        return adminNames;
    }



}
