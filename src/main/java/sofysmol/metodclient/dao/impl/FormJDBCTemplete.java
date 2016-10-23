package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.FormDao;
import sofysmol.metodclient.data.Form;
import sofysmol.metodclient.data.Form;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
@Repository
public class FormJDBCTemplete implements FormDao {
    private DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Form getForm(String code) {
        return this.jdbcTemplate.queryForObject("select * from form where code_form = \'"+ code +"\'",new FormMapper());
    }
    public List<Form> getForms(){
        return this.jdbcTemplate.query("select * from form",new FormMapper());
    }
    public void updateForm(Form form)
    {
        this.jdbcTemplate.update("update form set NameF = ? where code_form = ?" ,
                form.getName(), form.getCode());
    }

    public void deleteForm(String code){
        jdbcTemplate.update("delete from form where code_form = ?", code);
    }

    public void insertForm(Form form){
        jdbcTemplate.update("insert into form (code_form, NameF) values (?,?)",
                form.getCode(),form.getName());

    }
    private static final class FormMapper implements RowMapper<Form> {

        public Form mapRow(ResultSet rs, int rowNum) throws SQLException {
            Form Form = new Form();
            Form.setCode(rs.getString("code_form"));
            Form.setName(rs.getString("NameF"));
            return Form;
        }
    }

}
