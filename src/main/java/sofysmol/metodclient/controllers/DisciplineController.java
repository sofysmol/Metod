package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofysmol.metodclient.dao.interf.DisciplineDao;
import sofysmol.metodclient.data.Discipline;

import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
@RestController
public class DisciplineController {
    @Autowired
    DisciplineDao disciplineDao;

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
}
