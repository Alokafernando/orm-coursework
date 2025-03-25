package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.entity.User;
import lk.ijse.pos.model.AdminDTO;
import lk.ijse.pos.model.UserDTO;

import java.io.IOException;
import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> getAll();

    boolean save(UserDTO receptionistDTO) throws IOException;

    AdminDTO find(String text);

    boolean update(UserDTO userDTO) throws IOException;

    void delete(String id);

    UserDTO findCredential(String text) throws IOException;

    String status();
}
