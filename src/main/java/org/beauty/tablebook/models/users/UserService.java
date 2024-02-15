package org.beauty.tablebook.models.users;

import org.beauty.tablebook.controllers.users.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UsersRepository usersRepository;


    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UserDTO> findAllUsersDTO(){

        List<Users> usersList = usersRepository.findAll();

        List<UserDTO> userDTOList = new ArrayList<>(usersList.size());

        for (Users user: usersList){

            UserDTO userDTO = new UserDTO().fromEntityToDTO(user);

            userDTOList.add(userDTO);

        }

        return userDTOList;
    }

    public void save(UserDTO userDTO){

        if (usersRepository.findFirstByEmail(userDTO.getEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }

        Users user = userDTO.fromDTOtoEntity();
        user.setIsAdmin(false);
        usersRepository.save(user);

    }

    public Users    findByEmail(String email){
        Optional<Users> optionalUsers = usersRepository.findFirstByEmail(email);

        return optionalUsers.orElse(null);

    }

}
