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

     FactoryConfiguration factory = FactoryConfiguration.getInstance();

    public AdminDAOImpl() throws IOException {
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Session session = factory.getSession()) {
            Query<Admin> query = session.createQuery("FROM Admin", Admin.class);
            adminList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
    }

    @Override
    public boolean save(Admin entity) {
        try (Session session = factory.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Admin find(String name) {
        try (Session session = factory.getSession()) {
            return session.createQuery("FROM Admin WHERE username = :username", Admin.class)
                    .setParameter("username", name)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Admin entity) {
        try (Session session = factory.getSession()) {
            Transaction transaction = session.beginTransaction();
            Admin existingAdmin = session.get(Admin.class, entity.getUsername());

            if (existingAdmin != null) {
                existingAdmin.setPassword(entity.getPassword());
                session.update(existingAdmin);
                transaction.commit();
                return true;
            } else {
                System.out.println("Admin not found for update.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(String username) {
        try (Session session = factory.getSession()) {
            Transaction transaction = session.beginTransaction();
            Admin admin = session.get(Admin.class, username);
            if (admin != null) {
                session.delete(admin);
                transaction.commit();
            } else {
                System.out.println("Admin not found for deletion.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSession(Session session) {
        // This method should only be used if session management is externally controlled
    }

    @Override
    public String generateNewID() {
        return "";
    }

    @Override
    public List<String> getAdminNames() {
        List<String> adminNames = new ArrayList<>();
        try (Session session = factory.getSession()) {
            Query<String> query = session.createQuery("SELECT a.username FROM Admin a", String.class);
            adminNames = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminNames;
    }


    @Override
    public boolean updateUsername(String currentUsername, String newUsername) {
        try (Session session = factory.getSession()) {
            Admin admin = session.createQuery("FROM Admin WHERE username = :username", Admin.class)
                    .setParameter("username", currentUsername)
                    .uniqueResult();

            if (admin != null) {
                Transaction transaction = session.beginTransaction();
                admin.setUsername(newUsername);
                session.update(admin);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePassword(String currentUsername, String currentPassword, String newPassword) {
        Session session = factory.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query<Admin> query = session.createQuery("FROM Admin WHERE username = :username", Admin.class);
            query.setParameter("username", currentUsername);
            Admin admin = query.uniqueResult();

            if (admin != null && admin.getPassword().equals(currentPassword)) {
                admin.setPassword(newPassword);
                session.update(admin);
                transaction.commit();
                return true;
            }

            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }

    }


}
