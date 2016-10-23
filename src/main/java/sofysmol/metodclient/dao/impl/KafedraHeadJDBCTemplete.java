package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.KafedraHeadDao;
import sofysmol.metodclient.data.KafedraHead;
import sofysmol.metodclient.data.KafedraHead;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
//@Repository
public class KafedraHeadJDBCTemplete{ //implements KafedraHeadDao {
    /*private DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public KafedraHead getKafedraHead(String codeKaf) {
        return this.jdbcTemplate.queryForObject("select * from KafedraHead where code_kaf = \'"+ codeKaf +"\'",new KafedraHeadMapper());
    }

    public List<KafedraHead> getKafedraHeads(){
        return this.jdbcTemplate.query("select * from KafedraHead",new KafedraHeadMapper());
    }
    public void updateKafedraHead(KafedraHead kafedraHead)
    {
        this.jdbcTemplate.update("update kafedraHead set NameS = ? where code_spec = ?" ,
                kafedraHead.getName(), kafedraHead.getCode());
    }

    public void deleteKafedraHead(String code){
        jdbcTemplate.update("delete from kafedraHead where code_spec = ?", code);
    }

    public void insertKafedraHead(KafedraHead kafedraHead){
        jdbcTemplate.update("insert into kafedraHead (code_spec, NameS) values (?,?)",
                kafedraHead.getCode(),kafedraHead.getName());

    }
    private static final class KafedraHeadMapper implements RowMapper<KafedraHead> {

        public KafedraHead mapRow(ResultSet rs, int rowNum) throws SQLException {
            KafedraHead KafedraHead = new KafedraHead();
            KafedraHead.setPassport(rs.getInt("passport"));
            KafedraHead.setCodeKaf(rs.getString("code_kaf"));
            KafedraHead.setFio(rs.getString("fio"));
            KafedraHead.setDegree(rs.getString("degree"));
            KafedraHead.setCategory(rs.getString("categ"));
            return KafedraHead;
        }
    }*/

}
