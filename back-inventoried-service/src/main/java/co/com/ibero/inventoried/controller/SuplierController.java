package co.com.ibero.inventoried.controller;

import co.com.ibero.inventoried.business.SuplierService;
import co.com.ibero.inventoried.dto.ResponseSuccessDTO;
import co.com.ibero.inventoried.dto.SuplierDTO;
import co.com.ibero.inventoried.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.request.mappings}/v1/suplier")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class SuplierController {

    private final SuplierService suplierService;
    @PostMapping(value = "/save")
    public ResponseSuccessDTO saveSuplier(@RequestBody SuplierDTO suplierDTO){

        return suplierService.saveSuplier(suplierDTO);
    }

    @PutMapping(value = "/update")
    public ResponseSuccessDTO updateSuplier(@RequestBody SuplierDTO suplierDTO){

        return suplierService.updateSuplier(suplierDTO);
    }

    @GetMapping(value = "/getSuplierAll")
    public List<UserDTO> getAllSuplier(){
        return suplierService.getAllSuplier();
    }
}
