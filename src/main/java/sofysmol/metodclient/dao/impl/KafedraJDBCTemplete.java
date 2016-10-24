package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.KafedraDao;
import sofysmol.metodclient.data.Kafedra;
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

    public List<Kafedra> getKafedrasByFakultet(String code){
        return this.jdbcTemplate.query("select * from kafedra where code_fak = \'"+code+"\'", new KafedraMapper());
    }

    public void updateKafedra(Kafedra kafedra)
    {
        this.jdbcTemplate.update("update kafedra set NameK = ?, phone = ?, code_fak = ? where code_kaf = ?" ,
                kafedra.getName(), kafedra.getPhone(),kafedra.getCodeFak(), kafedra.getCode());
    }

    public void deleteKafedra(String code){
        jdbcTemplate.update("delete from kafedra where code_kaf = ?", code);
    }

    public void insertKafedra(Kafedra kafedra){
        jdbcTemplate.update("insert into kafedra (code_kaf, NameK, phone, code_fak) values (?,?,?,?)",
                kafedra.getCode(),kafedra.getName(), kafedra.getPhone(),kafedra.getCodeFak());

    }

    private static final class KafedraMapper implements RowMapper<Kafedra> {

        public Kafedra mapRow(ResultSet rs, int rowNum) throws SQLException {
            Kafedra kafedra = new Kafedra(rs.getString("code_kaf"),
                    rs.getString("NameK"),
                    rs.getString("phone"),
                    rs.getString("code_fak"));

            return kafedra;
        }
    }


}
