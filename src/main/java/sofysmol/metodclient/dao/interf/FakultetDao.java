package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Fakultet;

import javax.sql.DataSource;
import java.util.List;
//192.168.1.203
/**
 * Created by sofysmo on 08.10.16.
 */
public interface FakultetDao {
    Fakultet getFakultet(String code);
    List<Fakultet> getFakultets();
    void setDataSource(DataSource ds);
}
