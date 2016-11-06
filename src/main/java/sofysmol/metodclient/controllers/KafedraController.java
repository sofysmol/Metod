package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofysmol.metodclient.dao.interf.KafedraDao;
import sofysmol.metodclient.data.Kafedra;

import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
@RestController
public class KafedraController {
    @Autowired
    KafedraDao kafedraDao;

    @RequestMapping(value="/kafedras", params = {"code"}, method = RequestMethod.GET)
    ResponseEntity<Kafedra> getKafedraByCode(@RequestParam(value="code") String code) {
        return new ResponseEntity<Kafedra>(kafedraDao.getKafedra(code), HttpStatus.OK);
    }
    @RequestMapping(value = "/kafedras", method = RequestMethod.GET)
    ResponseEntity<List<Kafedra>> getKafedras() {
        return new ResponseEntity<List<Kafedra>>(kafedraDao.getKafedras(), HttpStatus.OK);
    }

    @RequestMapping(value = "/faculties/kafedras", params = {"codeFak"}, method = RequestMethod.GET)
    ResponseEntity<List<Kafedra>> getKafedrasByFakultets( @RequestParam(value="codeFak") String code){
       return new ResponseEntity<List<Kafedra>>(kafedraDao.getKafedrasByFakultet(code), HttpStatus.OK);
    }
    @RequestMapping(value = "/kafedras", method= RequestMethod.PUT)
    Kafedra putKafedra(@RequestBody Kafedra kafedra){
        kafedraDao.updateKafedra(kafedra);
        return kafedra;
    }
    @RequestMapping(value = "/kafedras", method = RequestMethod.DELETE)
    void deleteKafedra( @RequestParam(value="code") String code){
        kafedraDao.deleteKafedra(code);
    }

    @RequestMapping(value = "/kafedras", method = RequestMethod.POST)
    void insertKafedra(@RequestBody Kafedra kafedra){
        kafedraDao.insertKafedra(kafedra);
    }
}
