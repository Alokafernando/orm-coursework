package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Admin;

import java.util.ArrayList;
import java.util.List;

public interface AdminDAO extends CrudDAO<Admin> {
    List<String> getAdminNames();
    List<String> getAdminPasswords();
    boolean updateUsername(String currentUsername, String newUsername);
    boolean updatePassword(String currentUsername, String currentPassword, String newPassword);
}
