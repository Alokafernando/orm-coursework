package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.TherapistBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.TherapistDAO;
import lk.ijse.pos.entity.Therapist;
import lk.ijse.pos.model.TherapistDTO;
import lk.ijse.pos.tm.TherapistTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {

    TherapistDAO theropistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THEROPIST);

    public TherapistBOImpl() throws IOException {

    }

    @Override
    public List<TherapistDTO> getAll() {
        ArrayList<TherapistDTO> theropistDTOS = new ArrayList<>();
        List<Therapist> theropists = theropistDAO.getAll();
        for (Therapist theropist : theropists) {
            theropistDTOS.add(new TherapistDTO(theropist.getTherapistId(), theropist.getName(), theropist.getContact(), theropist.getEmail(), theropist.getStatus()));
        }
        return theropistDTOS;
    }

    @Override
    public void save(TherapistDTO theropistDTO) {
        theropistDAO.save(new Therapist(theropistDTO.getTheropistId(), theropistDTO.getName(), theropistDTO.getContact(), theropistDTO.getEmail(), theropistDTO.getStatus()));
    }

    @Override
    public TherapistTM findTheropist(String theropistId) {
        return theropistDAO.findTheropist( theropistId);
    }

    @Override
    public void update(TherapistDTO theropistDTO) {
        theropistDAO.update(new Therapist(theropistDTO.getTheropistId(), theropistDTO.getName(), theropistDTO.getContact(), theropistDTO.getEmail(), theropistDTO.getStatus()));
    }

    @Override
    public void delete(String id) {
        theropistDAO.delete(id);
    }


    @Override
    public String generateNewID() {
        return theropistDAO.generateNewID();
    }
}
