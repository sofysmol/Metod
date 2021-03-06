package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Discipline;
import sofysmol.metodclient.data.Sheduler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
public interface DisciplineDao {
    Discipline getDiscipline(String code);
    List<Discipline> getDisciplines();
    List<Discipline> getDisciplinesBySpecialty(String codeSpec, String codeForm, String codeKaf) throws SQLException;
    void setDataSource(DataSource ds);
    void updateDiscipline(Discipline fakultet);
    void deleteDiscipline(String code);
    void insertDiscipline(Discipline fakultet);
    void deleteSheduler(String semester, String codeDis,
                        String codeSpec, String codeKaf,
                        String codeForm);
    void insertDiscipline(String codeDis,String codeSpec,
                          String codeKaf,String codeForm,
                          Sheduler sheduler);

    void deleteDiscipline(String codeDis, String codeSpec, String codeKaf, String codeForm);

}
