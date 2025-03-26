package lk.ijse.pos.dao;

import org.hibernate.Session;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    List<T> getAll();

    boolean save(T entity);

    T find(String text);

    boolean update(T entity);

    void delete(String id);

    void setSession(Session session);

    String generateNewID();

}
