package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Fakultet;

import javax.sql.DataSource;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface FakultetDao {
    Fakultet getFakultet(String code);
    void setDataSource(DataSource ds);
}
