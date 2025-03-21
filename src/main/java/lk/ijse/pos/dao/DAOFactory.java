package lk.ijse.pos.dao;


import lk.ijse.pos.dao.custom.impl.AdminDAOImpl;

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
        ADMIN;
    }
    public SuperDAO getDAO(DAOType type) throws IOException {
        switch (type) {
            case ADMIN:
                return new AdminDAOImpl();
            default:
                return null;
        }
    }
}
