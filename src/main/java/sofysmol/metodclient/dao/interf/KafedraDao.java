package sofysmol.metodclient.dao.interf;


import sofysmol.metodclient.data.Kafedra;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface KafedraDao {
    Kafedra getKafedra(String code);
    List<Kafedra> getKafedras();
    void setDataSource(DataSource ds);

}
