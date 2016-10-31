package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofysmol.metodclient.dao.interf.DisciplineDao;
import sofysmol.metodclient.data.Discipline;
import sofysmol.metodclient.data.Discipline;
import sofysmol.metodclient.data.Specialty;

import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
@RestController
public class DisciplineController {
    @Autowired
    DisciplineDao disciplineDao;

    @RequestMapping("/disciplines/{code}")
    ResponseEntity<Discipline> getFacultetByCode(@PathVariable(value="code") String code) {
        return new ResponseEntity<Discipline>(disciplineDao.getDiscipline(code), HttpStatus.OK);
    }
    @RequestMapping("/disciplines")
    ResponseEntity<List<Discipline>> getSpetialty() {
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

    @RequestMapping(value = {"/disciplines"}, params = {"code-spec","code-form", "code-kaf"})
    ResponseEntity<List<Discipline>> getDisciplinesByCodeSpec(
            @RequestParam(value="code-spec") String codeSpec,
            @RequestParam(value="code-form") String codeForm,
            @RequestParam(value="code-kaf") String codeKaf){
        return new ResponseEntity<List<Discipline>>
                (disciplineDao.getDisciplinesByScpecialty(codeSpec,codeForm,codeKaf), HttpStatus.OK);
    }

}
