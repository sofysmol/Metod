package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofysmol.metodclient.dao.interf.FakultetDao;
import sofysmol.metodclient.dao.interf.KafedraDao;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.data.Fakultet;
import sofysmol.metodclient.data.Kafedra;
import sofysmol.metodclient.data.Specialty;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by sofysmo on 17.10.16.
 */
@RestController
public class FakultetController {
    @Autowired
    FakultetDao fakultetDao;


    @RequestMapping("/fakultets/{code}")
    ResponseEntity<Fakultet> getFacultetByCode(@PathVariable(value="code") String code) {
        return new ResponseEntity<Fakultet>(fakultetDao.getFakultet(code), HttpStatus.OK);
    }

    @RequestMapping("/fakultets")
    ResponseEntity<List<Fakultet>> getFacultets() {
        return new ResponseEntity<List<Fakultet>>(fakultetDao.getFakultets(), HttpStatus.OK);
    }
    @RequestMapping(value = "/fakultets/{code}", method= RequestMethod.PUT)
    ResponseEntity<Fakultet> putFakultet( @PathVariable(value="code") String code, @RequestBody Fakultet fakultet){
        //Fakultet fakultet = new Fakultet(code, name);
        fakultetDao.updateFakultet(fakultet);
        return new ResponseEntity<Fakultet>(fakultet, HttpStatus.OK);
    }
    @RequestMapping(value = "/fakultets/{code}", method = RequestMethod.DELETE)
    void deleteFakultet( @PathVariable(value="code") String code){
        fakultetDao.deleteFakultet(code);
    }

    @RequestMapping(value = "/fakultets", method = RequestMethod.POST)
    void addFakultet(@RequestBody Fakultet fakultet){
        fakultetDao.insertFakultet(fakultet);
    }


}
