package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.KafedraDao;
import sofysmol.metodclient.data.Kafedra;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
@Repository
public class KafedraJDBCTemplete implements KafedraDao {
    private DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Kafedra getKafedra(String code) {
        return this.jdbcTemplate.queryForObject("select * from kafedra where code_kaf = \'"+ code +"\'",new KafedraMapper());
    }

    public List<Kafedra> getKafedras(){
        return this.jdbcTemplate.query("select * from kafedra",new KafedraMapper());
    }

    private static final class KafedraMapper implements RowMapper<Kafedra> {

        public Kafedra mapRow(ResultSet rs, int rowNum) throws SQLException {
            Kafedra Kafedra = new Kafedra();
            Kafedra.setCode(rs.getString("code_kaf"));
            Kafedra.setName(rs.getString("NameK"));
            return Kafedra;
        }
    }

}
