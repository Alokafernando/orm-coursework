package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.AdminBO;
import lk.ijse.pos.config.FactoryConfiguration;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.AdminDAO;
import lk.ijse.pos.entity.Admin;
import lk.ijse.pos.model.AdminDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminBOImpl implements AdminBO {

    private final AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);

    public AdminBOImpl() throws IOException {}

    @Override
    public List<AdminDTO> getAll() {
        return null;
    }

    @Override
    public boolean save(AdminDTO adminDTO) throws IOException {
        return false;
    }

    @Override
    public AdminDTO find(String username) {
        return null;
    }

    @Override
    public boolean update(AdminDTO adminDTO) throws IOException {
        return false;
    }

    @Override
    public void delete(String username) {

    }

    @Override
    public AdminDTO findCredential(String username) throws IOException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            adminDAO.setSession(session);
            Admin admin = adminDAO.find(username);
            if (admin != null) {
                return new AdminDTO(admin.getAdminId(), admin.getUsername(), admin.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getAdminNames() throws IOException {
        List<String> adminNames = new ArrayList<>();
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            adminDAO.setSession(session);
            adminNames = adminDAO.getAdminNames();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminNames;
    }



    @Override
    public boolean updateUsername(String currentUsername, String newUsername) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        adminDAO.setSession(session);
        return adminDAO.updateUsername(currentUsername, newUsername);
    }

    @Override
    public boolean updatePassword(String currentUsername, String currentPassword, String newPassword) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        adminDAO.setSession(session);
        return adminDAO.updatePassword(currentUsername, currentPassword, newPassword);
    }


}
