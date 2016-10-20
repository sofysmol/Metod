package sofysmol.metodclient.dao.interf;

import sofysmol.metodclient.data.Form;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
public interface FormDao {
    Form getForm(String code);
    List<Form> getForms();
    void setDataSource(DataSource ds);
}
