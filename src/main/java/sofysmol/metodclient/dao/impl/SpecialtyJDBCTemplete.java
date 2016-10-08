package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.data.Specialty;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sofysmo on 08.10.16.
 */
@Repository
public class SpecialtyJDBCTemplete implements SpecialtyDao {
    private DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Specialty getSpecialty(String code) {
        return this.jdbcTemplate.queryForObject("select * from specialty where code_spec = \'"+ code +"\'",new SpecialtyMapper());
    }
    private static final class SpecialtyMapper implements RowMapper<Specialty> {

        public Specialty mapRow(ResultSet rs, int rowNum) throws SQLException {
            Specialty Specialty = new Specialty();
            Specialty.setCode(rs.getString("code_spec"));
            Specialty.setName(rs.getString("NameS"));
            return Specialty;
        }
    }

}