package co.com.ibero.inventoried.business.impl;

import co.com.ibero.inventoried.business.UserService;
import co.com.ibero.inventoried.dto.ResponseSuccessDTO;
import co.com.ibero.inventoried.dto.UserDTO;
import co.com.ibero.inventoried.exception.GenericException;
import co.com.ibero.inventoried.model.User;
import co.com.ibero.inventoried.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public ResponseSuccessDTO saveUser(UserDTO userDTO) {
        log.info("Inicio de metodo saveUser");
        var user = userRepository.userByDocumentNumber(userDTO.getDocumentNumber());
        if (user == null) {
            var userSave = User.builder().documentNumber(userDTO.getDocumentNumber()).firstName(userDTO.getFirstName()).secondName(userDTO.getSecondName()).
                    lastName(userDTO.getLastName()).createdAt(new Date()).email(userDTO.getEmail()).telephone(userDTO.getTelephone())
                    .address(userDTO.getAddress()).userType(userDTO.getUserType()).build();
            userRepository.save(userSave);
        } else {
            throw new GenericException("Not Found", "El usuario ya existe", "Lo sentimos.", HttpStatus.NOT_FOUND);
        }

        return ResponseSuccessDTO.builder().code(1L).message("Usuario creado correctamente").build();
    }

    public String dateNow() {
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha como una cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formatter);
    }

    @Override
    public ResponseSuccessDTO saveUpdate(UserDTO userDTO) {
        log.info("Inicio de metodo saveUpdate");
        var userSave = User.builder().id(userDTO.getId()).documentNumber(userDTO.getDocumentNumber()).firstName(userDTO.getFirstName()).secondName(userDTO.getSecondName()).
                lastName(userDTO.getLastName()).createdAt(new Date()).email(userDTO.getEmail()).telephone(userDTO.getTelephone())
                .address(userDTO.getAddress()).userType(userDTO.getUserType()).build();
        var save = userRepository.save(userSave);
        log.info("usuario ->{} " + save);
        if (save.getDocumentNumber() == null) {
            throw new GenericException("BAD_ERROR", "Error al actualizar el usuario", "Lo sentimos.", HttpStatus.BAD_REQUEST);
        }
        return ResponseSuccessDTO.builder().code(1L).message("Usuario actualizado correctamente").build();
    }

    @Override
    public List<UserDTO> getAllUser() {
        log.info("Inicio de metodo getAllUser");
        var users = userRepository.findAll();
        log.info("Users -> {} " + users);
        return convertUserListToDTOList((List<User>) users);
    }

    public List<UserDTO> convertUserListToDTOList(List<User> userList) {
        List<UserDTO> list = new ArrayList<>();

        for(User user : userList){
            var userDto = UserDTO.builder().firstName(user.getFirstName()).secondName(user.getSecondName()).lastName(user.getLastName())
                    .documentNumber(user.getDocumentNumber()).id(user.getId()).email(user.getEmail()).telephone(user.getTelephone())
                    .address(user.getAddress()).userType(user.getUserType()).build();
            list.add(userDto);
        }
        return list;
    }
}
