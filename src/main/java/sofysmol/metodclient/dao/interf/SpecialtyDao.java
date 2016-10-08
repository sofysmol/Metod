package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Specialty;

import javax.sql.DataSource;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface SpecialtyDao {
    Specialty getSpecialty(String code);
    void setDataSource(DataSource ds);
}
