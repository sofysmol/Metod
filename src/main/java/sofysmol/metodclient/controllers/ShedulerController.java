package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sofysmol.metodclient.dao.interf.ShedulerDao;
import sofysmol.metodclient.data.Discipline;
import sofysmol.metodclient.data.Sheduler;

import java.util.List;

/**
 * Created by sofysmo on 31.10.16.
 */
@RestController
public class ShedulerController {
    @Autowired
    private ShedulerDao shedulerDao;
    @RequestMapping(value = {"/sheduler"}, params = {"code-spec","code-form", "code-kaf", "code-dis"})
    ResponseEntity<List<Sheduler>> getDisciplinesByCodeSpec(
            @RequestParam(value="code-spec") String codeSpec,
            @RequestParam(value="code-form") String codeForm,
            @RequestParam(value="code-kaf") String codeKaf,
            @RequestParam(value="code-dis") String codeDis){
        return new ResponseEntity<List<Sheduler>>
                (shedulerDao.getSheduler(codeDis, codeKaf, codeSpec,codeForm), HttpStatus.OK);
    }
}
