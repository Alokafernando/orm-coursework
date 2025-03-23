package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.config.FactoryConfiguration;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.entity.Admin;
import lk.ijse.pos.entity.User;
import lk.ijse.pos.model.AdminDTO;
import lk.ijse.pos.model.UserDTO;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;


public class UserBOImpl implements UserBO {


    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    public UserBOImpl() throws IOException {
    }

    @Override
    public List<UserDTO> getAll() {
        return List.of();
    }

    @Override
    public boolean save(UserDTO receptionistDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        return userDAO.save(new User(receptionistDTO.getUsername(), receptionistDTO.getPassword()));
    }

    @Override
    public AdminDTO find(String text) {
        return null;
    }

    @Override
    public boolean update(UserDTO userDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        return userDAO.update(new User(userDTO.getUsername(), userDTO.getPassword()));
    }


    @Override
    public void delete(String id) {

    }

    @Override
    public UserDTO findCredential(String text) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        User user = userDAO.find(text);
        if (user !=null){
            return new UserDTO(
                    user.getUsername(),
                    user.getPassword()
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean status() {
        boolean isUser = true;
        return isUser;
    }
}
