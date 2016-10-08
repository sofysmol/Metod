package sofysmol.metodclient.dao.interf;


import sofysmol.metodclient.data.Kafedra;

import javax.sql.DataSource;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface KafedraDao {
    Kafedra getKafedra(String code);
    void setDataSource(DataSource ds);
}
