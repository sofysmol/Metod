package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Form;

import javax.sql.DataSource;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface FormDao {
    Form getForm(String code);
    void setDataSource(DataSource ds);
}
