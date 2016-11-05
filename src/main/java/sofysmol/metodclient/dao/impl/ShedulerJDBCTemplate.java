package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.ShedulerDao;
import sofysmol.metodclient.data.Sheduler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sofysmo on 31.10.16.
 */
@Repository
public class ShedulerJDBCTemplate implements ShedulerDao {
    private DataSource dataSource;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public List<Sheduler> getSheduler(String codeDis, String codeKaf, String codeSpec, String codeForm){
        java.util.Formatter f = new java.util.Formatter();
        return this.jdbcTemplate.query(
                f.format("select * from spec_dis_form" +
                                " where code_kaf=\'%s\' and code_form=\'%s\' " +
                                "and code_spec=\'%s\' and code_dis=\'%s\'",
                        codeKaf, codeForm, codeSpec, codeDis).toString(),
                new ShedulerJDBCTemplate.ShedulerMapper());
    }
    public void updateSheduler(String codeDis, String codeKaf, String codeSpec,
                               String codeForm, Sheduler sh){
                jdbcTemplate.update("update spec_dis_form set " +
                        " h_lec=?, h_lab=?, h_pr=?, h_kurs=?, report=? where code_dis=? " +
                        "and code_spec=? and code_kaf=? and code_form=? and semester=?",
                        sh.getLecture(),sh.getLab(), sh.getPrak(),
                        sh.getKurs(),sh.getReport(), codeDis,
                        codeSpec,codeKaf,codeForm, sh.getSemester());
    }
    private static final class ShedulerMapper implements RowMapper<Sheduler> {

        public Sheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sheduler sheduler = new Sheduler(rs.getString("semester"),
                                                rs.getString("h_lec"),
                                                rs.getString("h_lab"),
                                                rs.getString("h_pr"),
                                                rs.getString("h_kurs"),
                                                rs.getString("report"));
            return sheduler;
        }
    }
}
