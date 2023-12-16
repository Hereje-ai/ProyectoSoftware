package co.com.ibero.inventoried.business;

import co.com.ibero.inventoried.dto.SuplierDTO;
import co.com.ibero.inventoried.dto.UserDTO;

public interface SuplierService {

    <T> T saveSuplier(SuplierDTO suplierDTO);

    <T> T updateSuplier(SuplierDTO suplierDTO);

    <T> T getAllSuplier();
}
