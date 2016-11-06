package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.DisciplineDao;
import sofysmol.metodclient.data.Discipline;
import sofysmol.metodclient.data.Sheduler;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sofysmo on 23.10.16.
 */
@Repository
public class DisciplineJDBCTemplate implements DisciplineDao {
    private DataSource dataSource;


    @Autowired
    protected JdbcTemplate jdbcTemplate;

    SimpleJdbcCall disBySpecProc;
    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        disBySpecProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getDisciplinesBySpecialty")
                .returningResultSet("disciplines",
                        new DisciplineJDBCTemplate.DisciplineMapper());
    }

    public Discipline getDiscipline(String code) {
        return this.jdbcTemplate.queryForObject("select * from discipline where code_dis = \'"+ code +"\'",new DisciplineJDBCTemplate.DisciplineMapper());
    }
    public List<Discipline> getDisciplines(){
        return this.jdbcTemplate.query("select * from discipline",new DisciplineJDBCTemplate.DisciplineMapper());
    }

    public List<Discipline> getDisciplinesBySpecialty(String codeSpec, String codeForm, String codeKaf) throws SQLException{
        Formatter f = new Formatter();
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("codeSpec", codeSpec)
                .addValue("codeKaf", codeKaf)
                .addValue("codeForm", codeForm);
        Map m = disBySpecProc.execute(in);
        return (List) m.get("disciplines");
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
    public void insertDiscipline(String codeDis,String codeSpec,
                                 String codeKaf,String codeForm,
                                 Sheduler sh){
        jdbcTemplate.update("insert into spec_dis_form" +
                        " (code_dis, code_spec, code_kaf, code_form," +
                        " semester, h_lec, h_lab, h_pr, h_kurs, report)" +
                        " values (?,?,?,?,?,?,?,?,?,?)",
                codeDis, codeSpec, codeKaf, codeForm, sh.getSemester(),
                sh.getLecture(), sh.getLab(), sh.getPrak(), sh.getKurs(),
                sh.getReport());
    }
    public void deleteDiscipline(String codeDis,
                                 String codeSpec,
                                 String codeKaf,
                                 String codeForm){
        jdbcTemplate.update("delete from spec_dis_form" +
                        " where code_kaf=? AND code_spec=? AND code_form=? AND code_dis=?",
                codeKaf, codeSpec, codeForm, codeDis);
    }
    public void deleteSheduler(String semester, String codeDis,
                        String codeSpec, String codeKaf,
                        String codeForm){
        jdbcTemplate.update("delete from spec_dis_form" +
                        " where semester=? and code_dis = ? " +
                        "and code_spec=? and code_kaf=? and code_form=?",
                semester, codeDis,codeSpec, codeKaf, codeForm);
    }
    private static final class DisciplineMapper implements RowMapper<Discipline> {

        public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
            Discipline Discipline = new Discipline(rs.getString("code_dis"),
                    rs.getString("NameD"));
            return Discipline;
        }
    }
}
