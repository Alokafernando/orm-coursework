package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.model.TherapistDTO;
import lk.ijse.pos.tm.TherapistTM;

import java.util.List;

public interface TherapistBO extends SuperBO {
    List<TherapistDTO> getAll();

    void save(TherapistDTO theropistDTO);

    TherapistTM findTheropist(String text);

    void update(TherapistDTO theropistDTO);

    void delete(String id);

    String generateNewID();
}
