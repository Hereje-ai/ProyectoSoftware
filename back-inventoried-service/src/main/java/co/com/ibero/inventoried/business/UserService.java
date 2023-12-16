package co.com.ibero.inventoried.business;


import co.com.ibero.inventoried.dto.UserDTO;


public interface UserService {

    <T> T saveUser(UserDTO userDTO);

    <T> T saveUpdate(UserDTO userDTO);

    <T> T getAllUser();
}
