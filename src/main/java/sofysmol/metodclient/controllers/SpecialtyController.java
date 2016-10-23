package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sofysmol.metodclient.dao.interf.KafedraDao;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.data.Kafedra;
import sofysmol.metodclient.data.Specialty;

import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
@RestController
public class SpecialtyController {
    @Autowired
    SpecialtyDao specialtyDao;

    @RequestMapping("/specialties")
    ResponseEntity<List<Specialty>> getSpetialty() {
        return new ResponseEntity<List<Specialty>>(specialtyDao.getSpecialties(), HttpStatus.OK);
    }

    /*@RequestMapping(value = "/kafedra/{code}/specialty", method = RequestMethod.GET)
    void getSpetialtyByFakultet( @PathVariable(value="code") String code){
        // kafedraDao.
    }*/

}
