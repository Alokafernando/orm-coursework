package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Therapist;
import lk.ijse.pos.tm.TherapistTM;

public interface TherapistDAO extends CrudDAO<Therapist> {
    TherapistTM findTheropist(String id);
}
