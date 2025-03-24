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
        List<AdminDTO> adminDTOs = new ArrayList<>();
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            adminDAO.setSession(session);
            List<Admin> admins = adminDAO.getAll();
            for (Admin admin : admins) {
                adminDTOs.add(new AdminDTO(admin.getAdminId(), admin.getUsername(), admin.getPassword()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminDTOs;
    }

    @Override
    public boolean save(AdminDTO adminDTO) throws IOException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            adminDAO.setSession(session);
            return adminDAO.save(new Admin(adminDTO.getAdminId(), adminDTO.getUsername(), adminDTO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AdminDTO find(String username) {
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
    public boolean update(AdminDTO adminDTO) throws IOException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            adminDAO.setSession(session);
            Admin existingAdmin = adminDAO.find(adminDTO.getUsername());

            if (existingAdmin != null) {
                existingAdmin.setPassword(adminDTO.getPassword());
                return adminDAO.update(existingAdmin);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(String username) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            adminDAO.setSession(session);
            adminDAO.delete(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public List<String> getAdminPasswords() throws IOException {
        List<String> adminPasswords = new ArrayList<>();
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            adminDAO.setSession(session);
            adminPasswords = adminDAO.getAdminPasswords(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminPasswords;
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
