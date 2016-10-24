package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.DisciplineDao;
import sofysmol.metodclient.data.Discipline;
import sofysmol.metodclient.data.Discipline;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 23.10.16.
 */
@Repository
public class DisciplineJDBCTemplete implements DisciplineDao {
    private DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Discipline getDiscipline(String code) {
        return this.jdbcTemplate.queryForObject("select * from discipline where code_dis = \'"+ code +"\'",new DisciplineJDBCTemplete.DisciplineMapper());
    }
    public List<Discipline> getDisciplines(){
        return this.jdbcTemplate.query("select * from discipline",new DisciplineJDBCTemplete.DisciplineMapper());
    }
    public void updateDiscipline(Discipline fakultet)
    {
        this.jdbcTemplate.update("update discipline set NameD = ? where code_dis = ?" ,
                fakultet.getName(), fakultet.getCode());
    }

    public void deleteDiscipline(String code){
        jdbcTemplate.update("delete from discipline where code_dis = ?", code);
    }

    public void insertDiscipline(Discipline fakultet){
        jdbcTemplate.update("insert into discipline (code_dis, NameD) values (?,?)",
                fakultet.getCode(),fakultet.getName());

    }
    private static final class DisciplineMapper implements RowMapper<Discipline> {

        public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
            Discipline Discipline = new Discipline(rs.getString("code_dis"),
                    rs.getString("NameD"));
            return Discipline;
        }
    }
}
