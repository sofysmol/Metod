package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.data.Specialty;
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
    @RequestMapping(value = "/specialties/{code}", method= RequestMethod.PUT)
    Specialty putSpecialty( @PathVariable(value="code") String code,
                        @RequestBody Specialty specialty){
        specialtyDao.updateSpecialty(specialty);
        return specialty;
    }
    @RequestMapping(value = "/specialties/{code}", method = RequestMethod.DELETE)
    void deleteSpecialty( @PathVariable(value="code") String code){
        specialtyDao.deleteSpecialty(code);
    }
    @RequestMapping(value = "/specialties", method = RequestMethod.POST)
    void addSpecialty(@RequestBody Specialty specialty){;
        specialtyDao.insertSpecialty(specialty);
    }
    /*@RequestMapping(value = "/specialty/{code}/specialty", method = RequestMethod.GET)
    void getSpetialtyByFakultet( @PathVariable(value="code") String code){
        // specialtyDao.
    }*/

}
