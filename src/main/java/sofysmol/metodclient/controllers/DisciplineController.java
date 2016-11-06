package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofysmol.metodclient.dao.interf.DisciplineDao;
import sofysmol.metodclient.data.Discipline;
import sofysmol.metodclient.data.Discipline;
import sofysmol.metodclient.data.Sheduler;
import sofysmol.metodclient.data.Specialty;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
@RestController
public class DisciplineController {
    @Autowired
    DisciplineDao disciplineDao;

    @RequestMapping("/disciplines/{code}")
    ResponseEntity<Discipline> getDisciplineByCode(@PathVariable(value="code") String code) {
        return new ResponseEntity<Discipline>(disciplineDao.getDiscipline(code), HttpStatus.OK);
    }
    @RequestMapping("/disciplines")
    ResponseEntity<List<Discipline>> getDisciplines() {
        return new ResponseEntity<List<Discipline>>(disciplineDao.getDisciplines(), HttpStatus.OK);
    }
    @RequestMapping(value = "/disciplines/{code}", method= RequestMethod.PUT)
    Discipline putDiscipline( @PathVariable(value="code") String code,
                        @RequestBody Discipline discipline){
        disciplineDao.updateDiscipline(discipline);
        return discipline;
    }
    @RequestMapping(value = "/disciplines/{code}", method = RequestMethod.DELETE)
    void deleteDiscipline( @PathVariable(value="code") String code){
        disciplineDao.deleteDiscipline(code);
    }

    @RequestMapping(value = "/disciplines", method = RequestMethod.POST)
    void addDiscipline(@RequestBody Discipline discipline){;
        disciplineDao.insertDiscipline(discipline);
    }

    @RequestMapping(value = {"/disciplines"}, params = {"codeSpec","codeForm", "codeKaf"})
    ResponseEntity<List<Discipline>> getDisciplinesByCodeSpec(
            @RequestParam(value="codeSpec") String codeSpec,
            @RequestParam(value="codeForm") String codeForm,
            @RequestParam(value="codeKaf") String codeKaf) throws SQLException {
        return new ResponseEntity<List<Discipline>>
                (disciplineDao.getDisciplinesBySpecialty(codeSpec,codeForm,codeKaf), HttpStatus.OK);
    }
    @RequestMapping(value = "/specialties/discipline", method = RequestMethod.POST)
    void addDiscipline(@RequestParam("codeKaf") String codeKaf,
                       @RequestParam("codeForm") String codeForm,
                       @RequestParam("codeSpec") String codeSpec,
                       @RequestParam("code") String codeDis,
                       @RequestParam("lecture") String lecture,
                       @RequestParam("lab") String lab,
                       @RequestParam("prak") String prak,
                       @RequestParam("report") String report,
                       @RequestParam("kurs") String kurs,
                       @RequestParam("semester") String semester){
        Sheduler sheduler = new Sheduler(semester,lecture,prak,lab,kurs,report);
        disciplineDao.insertDiscipline(codeDis, codeSpec, codeKaf, codeForm, sheduler);
    }
    @RequestMapping(value = "/specialties/discipline", method = RequestMethod.DELETE)
    void deleteDiscipline(@RequestParam("codeKaf") String codeKaf,
                          @RequestParam("codeForm") String codeForm,
                          @RequestParam("codeSpec") String codeSpec,
                          @RequestParam("code") String codeDis){
        disciplineDao.deleteDiscipline(codeDis, codeSpec, codeKaf, codeForm);
    }
    @RequestMapping(value = "/disciplines/sheduler", method = RequestMethod.DELETE)
    void deleteSheduler(@RequestParam("codeKaf") String codeKaf,
                        @RequestParam("codeForm") String codeForm,
                        @RequestParam("codeDis") String codeDis,
                        @RequestParam("codeSpec") String codeSpec,
                        @RequestParam("semester") String semester){
        disciplineDao.deleteSheduler(semester, codeDis,codeSpec,codeKaf, codeForm);
    }


}
