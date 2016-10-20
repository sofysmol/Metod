package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sofysmol.metodclient.dao.interf.FakultetDao;
import sofysmol.metodclient.dao.interf.KafedraDao;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.data.Fakultet;
import sofysmol.metodclient.data.Kafedra;
import sofysmol.metodclient.data.Specialty;

import java.util.List;

/**
 * Created by sofysmo on 17.10.16.
 */
@RestController
public class DBController {
    @Autowired
    FakultetDao fakultetDao;

    @Autowired
    KafedraDao kafedraDao;

    @Autowired
    SpecialtyDao specialtyDao;

    @RequestMapping("/fakultet")
    ResponseEntity<Fakultet> getFacultet() {
        return new ResponseEntity<Fakultet>(fakultetDao.getFakultet("ФКТИ"), HttpStatus.OK);
    }

    @RequestMapping("/fakultets")
    ResponseEntity<List<Fakultet>> getFacultets() {
        return new ResponseEntity<List<Fakultet>>(fakultetDao.getFakultets(), HttpStatus.OK);
    }
    @RequestMapping("/kafedras")
    ResponseEntity<List<Kafedra>> getKafedras() {
        return new ResponseEntity<List<Kafedra>>(kafedraDao.getKafedras(), HttpStatus.OK);
    }

    @RequestMapping("/specialties")
    ResponseEntity<List<Specialty>> getSpecialties() {
        return new ResponseEntity<List<Specialty>>(specialtyDao.getSpecialties(), HttpStatus.OK);
    }

}
