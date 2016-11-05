package sofysmol.metodclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofysmol.metodclient.dao.interf.FacultyDao;
import sofysmol.metodclient.data.Faculty;

import java.util.List;

/**
 * Created by sofysmo on 17.10.16.
 */
@RestController
public class FacultyController {
    @Autowired
    FacultyDao fakultetDao;


    @RequestMapping(value = "/faculties", params = {"code"})
    ResponseEntity<Faculty> getFacultetByCode(@RequestParam(value="code") String code) {
        return new ResponseEntity<Faculty>(fakultetDao.getFaculty(code), HttpStatus.OK);
    }

    @RequestMapping("/faculties")
    ResponseEntity<List<Faculty>> getFaculties() {
        return new ResponseEntity<List<Faculty>>(fakultetDao.getFaculties(), HttpStatus.OK);
    }
    @RequestMapping(value = "/faculties", method= RequestMethod.PUT)
    ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty){
        fakultetDao.updateFaculty(faculty);
        return new ResponseEntity<Faculty>(faculty, HttpStatus.OK);
    }
    @RequestMapping(value = "/faculties", method = RequestMethod.DELETE)
    void deleteFaculty( @RequestParam(value="code") String code){
        fakultetDao.deleteFaculty(code);
    }

    @RequestMapping(value = "/faculties", method = RequestMethod.POST)
    void addFaculty(@RequestBody Faculty faculty){
        fakultetDao.insertFaculty(faculty);
    }


}
