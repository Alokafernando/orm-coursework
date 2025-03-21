package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.model.AdminDTO;

import java.util.List;

public interface AdminBO extends SuperBO {
    List<AdminDTO> getAll();

    boolean save(AdminDTO entity);

    AdminDTO find(String text);

    void update(String text);

    void delete(String id);

    AdminDTO findCredential(String text);
}
