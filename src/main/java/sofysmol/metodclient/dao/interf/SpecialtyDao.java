package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Specialty;
import sofysmol.metodclient.data.Specialty;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface SpecialtyDao {
    Specialty getSpecialty(String code);
    List<Specialty> getSpecialties();
    void updateSpecialty(Specialty fakultet);
    void deleteSpecialty(String code);
    void insertSpecialty(Specialty fakultet);
    
    void setDataSource(DataSource ds);
}
