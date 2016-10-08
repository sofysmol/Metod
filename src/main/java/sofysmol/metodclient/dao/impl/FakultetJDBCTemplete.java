package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.data.Fakultet;
import sofysmol.metodclient.dao.interf.FakultetDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private static final class FakultetMapper implements RowMapper<Fakultet> {

        public Fakultet mapRow(ResultSet rs, int rowNum) throws SQLException {
            Fakultet Fakultet = new Fakultet();
            Fakultet.setCode(rs.getString("code_fak"));
            Fakultet.setName(rs.getString("NameF"));
            return Fakultet;
        }
    }

}
