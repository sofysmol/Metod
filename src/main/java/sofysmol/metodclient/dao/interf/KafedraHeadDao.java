package sofysmol.metodclient.dao.interf;


import sofysmol.metodclient.data.Kafedra;
import sofysmol.metodclient.data.KafedraHead;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface KafedraHeadDao {
    KafedraHead getKafedraHead(String code);
    List<KafedraHead> getKafedraHeads();
    void setDataSource(DataSource ds);
}
