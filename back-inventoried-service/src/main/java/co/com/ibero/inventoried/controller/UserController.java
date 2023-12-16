package co.com.ibero.inventoried.controller;

import co.com.ibero.inventoried.business.UserService;
import co.com.ibero.inventoried.dto.ResponseSuccessDTO;
import co.com.ibero.inventoried.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.request.mappings}/v1/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/save")
    public ResponseSuccessDTO saveUser(@RequestBody UserDTO userDTO){

        return userService.saveUser(userDTO);
    }

    @PutMapping(value = "/update")
    public ResponseSuccessDTO saveUpdate(@RequestBody UserDTO userDTO){

        return userService.saveUpdate(userDTO);
    }

    @GetMapping(value = "/getUserAll")
    public List<UserDTO> getAllUser(){
        return userService.getAllUser();
    }

}
