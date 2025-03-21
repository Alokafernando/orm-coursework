package lk.ijse.pos.bo;


import lk.ijse.pos.bo.custom.impl.AdminBOImpl;

import java.io.IOException;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }
    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOType {
        ADMIN;
    }
    public SuperBO getBO(BOType type) throws IOException {
        switch (type) {
            case ADMIN:
                return new AdminBOImpl();
            default:
                return null;
        }
    }
}
