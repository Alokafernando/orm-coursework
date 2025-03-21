package lk.ijse.pos.dao;

import org.hibernate.Session;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    List<T> getAll();

    boolean save(T entity);

    T find(String text);

    void update(String text, T entity);

    void delete(String id);

    void setSession(Session session);


}
