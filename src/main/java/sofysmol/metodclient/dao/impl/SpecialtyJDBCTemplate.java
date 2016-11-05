package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.SpecialtyDao;
import sofysmol.metodclient.data.Sheduler;
import sofysmol.metodclient.data.Specialty;
import sofysmol.metodclient.data.Specialty;
import sofysmol.metodclient.data.Specialty;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

/**
 * Created by sofysmo on 08.10.16.
 */
@Repository
public class SpecialtyJDBCTemplate implements SpecialtyDao {
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
    public Specialty getSpecialty(String codeKaf, String codeForm, String code){
        Formatter f = new Formatter();
        return this.jdbcTemplate.queryForObject(
                f.format("select * from SpecByKafView" +
                " where code_kaf=\'%s\' and code_form=\'%s\' and code_spec=\'%s\'",
                        codeKaf, codeForm, code).toString(),
                new SpecialtyMapper());
    }
    public List<Specialty> getSpecialtiesByKafedra(String code) throws SQLException{
        return this.jdbcTemplate.query("select * from SpecByKafView where code_kaf=\'"+code+"\'",new SpecialtyMapper());
    }
    public void updateSpecialty(Specialty specialty)
    {
        this.jdbcTemplate.update("update specialty set NameS = ?, qualification = ? where code_spec = ?" ,
                specialty.getName(), specialty.getQualification(), specialty.getCode());
    }

    public void deleteSpecialty(String code){
        jdbcTemplate.update("delete from specialty where code_spec = ?", code);
    }

    public void insertSpecialty(Specialty specialty){
        jdbcTemplate.update("insert into specialty (code_spec, NameS, qualification) values (?,?,?)",
                specialty.getCode(),specialty.getName(), specialty.getQualification());
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
    private static final class SpecialtyMapper implements RowMapper<Specialty> {

        public Specialty mapRow(ResultSet rs, int rowNum) throws SQLException {
            String codeSpec = rs.getString("code_spec");
            String name = rs.getString("NameS");
            String codeKaf = null;
            String qualification = null;
            String codeForm = null;
            try {codeKaf = rs.getString("code_kaf");
            }catch (SQLException e){ }
            try {qualification = rs.getString("qualification");
            }catch (SQLException e){ }
            try {codeForm = rs.getString("code_form");
            }catch (SQLException e){ }
            Specialty specialty = new Specialty(codeSpec,name,codeKaf,codeForm,qualification);
            return specialty;
        }
    }

}
