package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Faculty;

import javax.sql.DataSource;
import java.util.List;
//192.168.1.203
/**
 * Created by sofysmo on 08.10.16.
 */
public interface FacultyDao {
    Faculty getFaculty(String code);
    List<Faculty> getFaculties();
    void setDataSource(DataSource ds);
    void updateFaculty(Faculty faculty);
    void deleteFaculty(String code);
    void insertFaculty(Faculty faculty);
}
