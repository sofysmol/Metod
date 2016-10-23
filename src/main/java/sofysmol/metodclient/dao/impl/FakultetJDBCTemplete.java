package sofysmol.metodclient.dao.impl;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.data.Fakultet;
import sofysmol.metodclient.dao.interf.FakultetDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
@Repository
public class FakultetJDBCTemplete implements FakultetDao {
    private DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Fakultet getFakultet(String code) {
        return this.jdbcTemplate.queryForObject("select * from facultet where code_fak = \'"+ code +"\'",new FakultetMapper());
    }
    public List<Fakultet> getFakultets(){
        return this.jdbcTemplate.query("select * from facultet",new FakultetMapper());
    }
    public void updateFakultet(Fakultet fakultet)
    {
        this.jdbcTemplate.update("update facultet set NameF = ? where code_fak = ?" ,
                fakultet.getName(), fakultet.getCode());
    }

    public void deleteFakultet(String code){
        jdbcTemplate.update("delete from facultet where code_fak = ?", code);
    }

    public void insertFakultet(Fakultet fakultet){
        jdbcTemplate.update("insert into facultet (code_fak, NameF) values (?,?)",
                fakultet.getCode(),fakultet.getName());

    }
    private static final class FakultetMapper implements RowMapper<Fakultet> {

        public Fakultet mapRow(ResultSet rs, int rowNum) throws SQLException {
            Fakultet Fakultet = new Fakultet(rs.getString("code_fak"),
                    rs.getString("NameF"));
            return Fakultet;
        }
    }

}
