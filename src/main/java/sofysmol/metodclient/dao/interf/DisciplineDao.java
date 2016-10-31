package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Discipline;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
public interface DisciplineDao {
    Discipline getDiscipline(String code);
    List<Discipline> getDisciplines();
    List<Discipline> getDisciplinesByScpecialty(String codeSpec, String codeForm, String codeKaf);
    void setDataSource(DataSource ds);
    void updateDiscipline(Discipline fakultet);
    void deleteDiscipline(String code);
    void insertDiscipline(Discipline fakultet);

}
