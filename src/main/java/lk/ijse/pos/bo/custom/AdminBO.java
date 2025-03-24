package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.model.AdminDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface AdminBO extends SuperBO {
    List<AdminDTO> getAll();

    boolean save(AdminDTO entity) throws IOException;

    AdminDTO find(String text);

    boolean update(AdminDTO adminDTO) throws IOException;

    void delete(String id);

    AdminDTO findCredential(String text) throws IOException;

    List<String> getAdminNames() throws IOException;

    List<String> getAdminPasswords() throws IOException;

    boolean updateUsername(String currentUsername, String newUsername) throws IOException;

    boolean updatePassword(String currentUsername, String currentPassword, String newPassword) throws IOException;
}
