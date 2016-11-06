package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.data.Sheduler;
import sofysmol.metodclient.data.Specialty;
import sofysmol.metodclient.data.Specialty;
import sofysmol.metodclient.data.Specialty;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
@RestController
public class SpecialtyController {
    @Autowired
    SpecialtyDao specialtyDao;

    @RequestMapping(value = {"/specialties"}, method = RequestMethod.GET)
    ResponseEntity<List<Specialty>> getSpetialties() {
        return new ResponseEntity<List<Specialty>>(specialtyDao.getSpecialties(), HttpStatus.OK);
    }
    @RequestMapping(value ={"/specialties"}, params = {"codeKaf", "codeForm", "codeSpec"},
            method = RequestMethod.GET)
    ResponseEntity<Specialty> getSpetialtyByKafAndForm(@RequestParam("codeKaf") String codeKaf,
                                                       @RequestParam("codeForm") String codeForm,
                                                       @RequestParam("codeSpec") String codeSpec) {
        Specialty s = specialtyDao.getSpecialty(codeKaf, codeForm, codeSpec);
        return new ResponseEntity<Specialty>(s, HttpStatus.OK);
    }
    @RequestMapping(value = "/specialties", params={"codeKaf"})
    ResponseEntity<List<Specialty>> getSpecialtysByKaf(@RequestParam("codeKaf") String code) throws SQLException{
        return new ResponseEntity<List<Specialty>>(specialtyDao.getSpecialtiesByKafedra(code), HttpStatus.OK);
    }
    @RequestMapping(value = "/specialties", method= RequestMethod.PUT)
    Specialty putSpecialty(@RequestBody Specialty specialty){
        specialtyDao.updateSpecialty(specialty);
        return specialty;
    }
    @RequestMapping(value = "/specialties", params = {"code"}, method = RequestMethod.DELETE)
    void deleteSpecialty(@RequestParam(value="code") String code) {
        specialtyDao.deleteSpecialty(code);
    }

    @RequestMapping(value = "/specialties", method = RequestMethod.POST)
    void addSpecialty(@RequestBody Specialty specialty){;
        specialtyDao.insertSpecialty(specialty);
    }
    @RequestMapping(value = "/kafedras/specialty", params = {"codeKaf","code","codeForm"},method = RequestMethod.POST)
    void addSpecialty(@RequestParam("codeKaf") String codeKaf,
                      @RequestParam("code") String codeSpec,
                      @RequestParam("codeForm") String codeForm){
        specialtyDao.insertSpecialty(codeSpec, codeKaf, codeForm);
    }
    @RequestMapping(value = "/kafedras/specialty", method = RequestMethod.DELETE)
    void deleteSpecialty(@RequestParam("codeKaf") String codeKaf,
                         @RequestParam("code") String codeSpec,
                         @RequestParam("codeForm") String codeForm){
        specialtyDao.deleteSpecialty(codeSpec, codeKaf, codeForm);
    }


    /*@RequestMapping(value = "/specialty/{code}/specialty", method = RequestMethod.GET)
    void getSpetialtyByFakultet( @PathVariable(value="code") String code){
        // specialtyDao.
    }*/

}
