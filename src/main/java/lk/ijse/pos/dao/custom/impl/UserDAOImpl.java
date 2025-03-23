package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.config.FactoryConfiguration;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static FactoryConfiguration factory;
    private Session session;

    public UserDAOImpl() throws IOException {
        factory = FactoryConfiguration.getInstance();
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public boolean save(User receptionist) {
        session = factory.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(receptionist);
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
    public User find(String name) {
        session = factory.getSession();
        User receptionist = session.get(User.class, name);
        return receptionist;
    }

    @Override
    public boolean update(User entity) {
        session = factory.getSession();
        try {
            User receptionist = session.get(User.class, entity);
            Transaction transaction = session.beginTransaction();

            receptionist.setUsername(entity.getUsername());
            receptionist.setPassword(entity.getPassword());

            transaction.commit();
            return receptionist != null;
        } catch (Exception e) {
            System.out.println("Receptionist update failed");
            return false;
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
