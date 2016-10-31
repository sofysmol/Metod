package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofysmol.metodclient.dao.interf.KafedraDao;
import sofysmol.metodclient.dao.interf.KafedraDao;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.data.Fakultet;
import sofysmol.metodclient.data.Kafedra;
import sofysmol.metodclient.data.Kafedra;
import sofysmol.metodclient.data.Specialty;

import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
@RestController
public class KafedraController {
    @Autowired
    KafedraDao kafedraDao;

    @RequestMapping("/kafedras/{code}")
    ResponseEntity<Kafedra> getKafedraByCode(@PathVariable(value="code") String code) {
        return new ResponseEntity<Kafedra>(kafedraDao.getKafedra(code), HttpStatus.OK);
    }
    @RequestMapping("/kafedras")
    ResponseEntity<List<Kafedra>> getKafedras() {
        return new ResponseEntity<List<Kafedra>>(kafedraDao.getKafedras(), HttpStatus.OK);
    }

    @RequestMapping(value = "/fakultets/{code}/kafedras", method = RequestMethod.GET)
    ResponseEntity<List<Kafedra>> getKafedrasByFakultets( @PathVariable(value="code") String code){
       return new ResponseEntity<List<Kafedra>>(kafedraDao.getKafedrasByFakultet(code), HttpStatus.OK);
    }
    @RequestMapping(value = "/kafedras/{code}", method= RequestMethod.PUT)
    Kafedra putKafedra( @PathVariable(value="code") String code,
                        @RequestBody Kafedra kafedra){
        kafedraDao.updateKafedra(kafedra);
        return kafedra;
    }
    @RequestMapping(value = "/kafedras/{code}", method = RequestMethod.DELETE)
    void deleteKafedra( @PathVariable(value="code") String code){
        kafedraDao.deleteKafedra(code);
    }

    @RequestMapping(value = "/kafedras", method = RequestMethod.POST)
    void addKafedra(@RequestBody Kafedra kafedra){
        kafedraDao.insertKafedra(kafedra);
    }

}
