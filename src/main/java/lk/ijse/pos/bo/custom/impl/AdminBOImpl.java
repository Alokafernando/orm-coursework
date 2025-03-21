package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.AdminBO;
import lk.ijse.pos.config.FactoryConfiguration;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.AdminDAO;
import lk.ijse.pos.entity.Admin;
import lk.ijse.pos.model.AdminDTO;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

public class AdminBOImpl implements AdminBO {

    private final AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);

    public AdminBOImpl() throws IOException {
    }

    @Override
    public List<AdminDTO> getAll() {
        return List.of();
    }

    @Override
    public boolean save(AdminDTO adminDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        adminDAO.setSession(session);
        return adminDAO.save(new Admin(adminDTO.getUsername(), adminDTO.getPassword()));
    }

    @Override
    public AdminDTO find(String text) {
        return null;
    }

    @Override
    public void update(String text) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public AdminDTO findCredential(String text) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        adminDAO.setSession(session);
        Admin admin = adminDAO.find(text);
        if (admin!=null){
            return new AdminDTO(
                    admin.getUsername(),
                    admin.getPassword()
            );
        }else {
            return null;
        }
    }
}
