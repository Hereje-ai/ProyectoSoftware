package co.com.ibero.inventoried.business.impl;

import co.com.ibero.inventoried.business.SuplierService;
import co.com.ibero.inventoried.dto.ResponseSuccessDTO;
import co.com.ibero.inventoried.dto.SuplierDTO;
import co.com.ibero.inventoried.dto.UserDTO;
import co.com.ibero.inventoried.exception.GenericException;
import co.com.ibero.inventoried.model.Suplier;
import co.com.ibero.inventoried.model.User;
import co.com.ibero.inventoried.repository.SuplierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SuplierServiceImpl implements SuplierService {

    private final SuplierRepository suplierRepository;

    @Override
    public ResponseSuccessDTO saveSuplier(SuplierDTO suplierDTO) {
        log.info("Inicio de metodo saveSuplier");
            var suplierSave = Suplier.builder().address(suplierDTO.getAddress()).email(suplierDTO.getEmail()).name(suplierDTO.getName())
                    .telephone(suplierDTO.getTelephone()).build();
            var validate = suplierRepository.save(suplierSave);

            if(validate.getName() == null){
                throw new GenericException("BAD_ERROR", "Error al crear el proveedor", "Lo sentimos.", HttpStatus.BAD_REQUEST);
            }
                return ResponseSuccessDTO.builder().code(1L).message("Proveedor creado correctamente").build();

    }

    @Override
    public ResponseSuccessDTO updateSuplier(SuplierDTO suplierDTO) {
        log.info("Inicio de metodo updateSuplier");
        var suplierSave = Suplier.builder().id(suplierDTO.getId()).address(suplierDTO.getAddress()).email(suplierDTO.getEmail()).name(suplierDTO.getName())
                .telephone(suplierDTO.getTelephone()).build();
        var validate = suplierRepository.save(suplierSave);

        if(validate.getName() == null){
            throw new GenericException("BAD_ERROR", "Error al crear el proveedor", "Lo sentimos.", HttpStatus.BAD_REQUEST);
        }
            return ResponseSuccessDTO.builder().code(1L).message("Proveedor actualizado correctamente").build();

    }

    @Override
    public List<SuplierDTO> getAllSuplier() {
        log.info("Inicio de metodo getAllSuplier");
        var supliers = suplierRepository.findAll();
        log.info("supliers -> {} " + supliers);
        return convertUserListToDTOList((List<Suplier>) supliers);
    }

    public List<SuplierDTO> convertUserListToDTOList(List<Suplier> suplierList) {
        List<SuplierDTO> list = new ArrayList<>();

        for(Suplier suplier : suplierList){
            var suplierDTO = SuplierDTO.builder().id(suplier.getId()).address(suplier.getAddress()).email(suplier.getEmail())
                    .name(suplier.getName()).telephone(suplier.getTelephone()).build();
            list.add(suplierDTO);
        }
        return list;
    }
}
