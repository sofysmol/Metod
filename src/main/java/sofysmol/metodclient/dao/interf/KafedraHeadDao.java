package sofysmol.metodclient.dao.interf;


import sofysmol.metodclient.data.KafedraHead;

import javax.sql.DataSource;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface KafedraHeadDao {
    KafedraHead getKafedraHead(String code);
    void setDataSource(DataSource ds);
}
