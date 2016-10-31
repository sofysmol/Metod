package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Sheduler;

import javax.sql.DataSource;
import java.util.List;
//192.168.1.203

/**
 * Created by sofysmo on 08.10.16.
 */
public interface ShedulerDao {
    List<Sheduler> getSheduler(String codeDis, String codeKaf, String codeSpec, String codeForm);
}
