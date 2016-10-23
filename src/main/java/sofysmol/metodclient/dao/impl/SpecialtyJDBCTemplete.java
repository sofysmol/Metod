package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.data.Specialty;
import sofysmol.metodclient.data.Specialty;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public List<Specialty> getSpecialties(){
        return this.jdbcTemplate.query("select * from specialty",new SpecialtyMapper());
    }
    public void updateSpecialty(Specialty specialty)
    {
        this.jdbcTemplate.update("update specialty set NameS = ? where code_spec = ?" ,
                specialty.getName(), specialty.getCode());
    }

    public void deleteSpecialty(String code){
        jdbcTemplate.update("delete from specialty where code_spec = ?", code);
    }

    public void insertSpecialty(Specialty specialty){
        jdbcTemplate.update("insert into specialty (code_spec, NameS, qualification) values (?,?,?)",
                specialty.getCode(),specialty.getName(), specialty.getQualification());

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
