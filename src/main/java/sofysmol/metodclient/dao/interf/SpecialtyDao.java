package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Kafedra;
import sofysmol.metodclient.data.Sheduler;
import sofysmol.metodclient.data.Specialty;
import sofysmol.metodclient.data.Specialty;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface SpecialtyDao {
    Specialty getSpecialty(String code);
    List<Specialty> getSpecialties();
    Specialty getSpecialty(String codeKaf, String codeForm, String code);
    List<Specialty> getSpecialtiesByKafedra(String code) throws SQLException;
    void updateSpecialty(Specialty fakultet);
    void deleteSpecialty(String code);
    void insertSpecialty(Specialty fakultet);
    void insertSpecialty(String codeSpec, String codeKaf,String codeForm);
    void deleteSpecialty(String codeSpec, String codeKaf,String codeForm);
    void insertDiscipline(String codeDis,String codeSpec,
                          String codeKaf,String codeForm,
                          Sheduler sheduler);
    void setDataSource(DataSource ds);
    void deleteDiscipline(String codeDis, String codeSpec, String codeKaf, String codeForm);
}
