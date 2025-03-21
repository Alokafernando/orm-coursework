package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.config.FactoryConfiguration;
import lk.ijse.pos.dao.custom.AdminDAO;
import lk.ijse.pos.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    private static FactoryConfiguration factory;
    private Session session;

    public AdminDAOImpl() throws IOException {
        if (factory == null) {
            factory = FactoryConfiguration.getInstance();
        }
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
        session = factory.getSession();  // Use the initialized session
        Admin admin = session.get(Admin.class, name);
        return admin;
    }

    @Override
    public void update(String name, Admin entity) {
        session = factory.getSession();
        try {
            Admin admin = session.get(Admin.class, name);
            Transaction transaction = session.beginTransaction();

            admin.setUsername(entity.getUsername());
            admin.setPassword(entity.getPassword());

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Customer update failed");
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
}
