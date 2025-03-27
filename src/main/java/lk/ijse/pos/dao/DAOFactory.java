package lk.ijse.pos.dao;


import lk.ijse.pos.dao.custom.impl.AdminDAOImpl;
import lk.ijse.pos.dao.custom.impl.TherapistDAOImpl;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;

import java.io.IOException;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {

    }
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOType {
    ADMIN, USER, THEROPIST;
    }
    public SuperDAO getDAO(DAOType type) throws IOException {
        switch (type) {
            case ADMIN:
                return new AdminDAOImpl();
            case USER:
                return new UserDAOImpl();
            case THEROPIST:
                return new TherapistDAOImpl();
            default:
                return null;
        }
    }
}
