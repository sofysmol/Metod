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

    private SimpleJdbcCall getSpecProc;
    private SimpleJdbcCall getSpecsProc;
    private SimpleJdbcCall updateSpecProc;
    private SimpleJdbcCall deleteSpecProc;
    private SimpleJdbcCall deleteSpecByKafAndFormProc;
    private SimpleJdbcCall insertSpecProc;
    private SimpleJdbcCall insertSpecByKafAndFormProc;
    private SimpleJdbcCall getSpecByKafAndFormProc;
    private SimpleJdbcCall getSpecsByKafProc;

    
    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        getSpecProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getSpecialty")
                .returningResultSet("specialty",
                        new SpecialtyMapper());
        getSpecByKafAndFormProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getSpecByKafAndForm")
                .returningResultSet("specialty",
                        new SpecialtyMapper());
        getSpecsByKafProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getSpecsByKaf")
                .returningResultSet("specialties",
                        new SpecialtyMapper());
        getSpecsProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getSpecialties")
                .returningResultSet("specialties",
                        new SpecialtyMapper());
        updateSpecProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("updateSpecialty");
        deleteSpecProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("deleteSpecialty");
        deleteSpecByKafAndFormProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("deleteSpecByKafAndForm");
        insertSpecProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insertSpecialty");
        insertSpecByKafAndFormProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insertSpecByKafAndForm");
    }

    public Specialty getSpecialty(String code) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code);
        Map m = getSpecProc.execute(in);
        return ((List<Specialty>) m.get("specialty")).get(0);
        //return this.jdbcTemplate.queryForObject("select * from specialty where code_spec = \'"+ code +"\'",new SpecialtyMapper());
    }
    public List<Specialty> getSpecialties(){
        Map m = getSpecsProc.execute();
        return ((List<Specialty>) m.get("specialties"));
        //return this.jdbcTemplate.query("select * from specialty",new SpecialtyMapper());
    }
    public Specialty getSpecialty(String codeKaf, String codeForm, String code){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code)
                .addValue("codeKaf", codeKaf)
                .addValue("codeForm", codeForm);
        Map m = getSpecByKafAndFormProc.execute(in);
        return ((List<Specialty>) m.get("specialty")).get(0);
        /*Formatter f = new Formatter();
        return this.jdbcTemplate.queryForObject(
                f.format("select * from SpecByKafView" +
                " where code_kaf=\'%s\' and code_form=\'%s\' and code_spec=\'%s\'",
                        codeKaf, codeForm, code).toString(),
                new SpecialtyMapper());*/
    }
    public List<Specialty> getSpecialtiesByKafedra(String code) throws SQLException{
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("codeKaf", code);
        Map m = getSpecsByKafProc.execute(in);
        return ((List<Specialty>) m.get("specialties"));
       // return this.jdbcTemplate.query("select * from SpecByKafView where code_kaf=\'"+code+"\'",new SpecialtyMapper());
    }
    public void updateSpecialty(Specialty specialty) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", specialty.getCode())
                .addValue("name", specialty.getName())
                .addValue("qualification", specialty.getQualification());
        updateSpecProc.execute(in);
        //this.jdbcTemplate.update("update specialty set NameS = ?, qualification = ? where code_spec = ?" ,
         //       specialty.getName(), specialty.getQualification(), specialty.getCode());
    }

    public void deleteSpecialty(String code) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code);
        deleteSpecProc.execute(in);
        //jdbcTemplate.update("delete from specialty where code_spec = ?", code);
    }

    public void insertSpecialty(Specialty specialty){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", specialty.getCode())
                .addValue("name", specialty.getName())
                .addValue("qualification", specialty.getQualification());
        insertSpecProc.execute(in);
        //jdbcTemplate.update("insert into specialty (code_spec, NameS, qualification) values (?,?,?)",
        //        specialty.getCode(),specialty.getName(), specialty.getQualification());
    }
    public void insertSpecialty(String codeSpec, String codeKaf,String codeForm){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", codeSpec)
                .addValue("codeKaf", codeKaf)
                .addValue("codeForm", codeForm);
        insertSpecByKafAndFormProc.execute(in);
        //jdbcTemplate.update("insert into kaf_spec_form (code_kaf, code_spec, code_form) values (?,?,?)",
        //        codeKaf,codeSpec, codeForm);
    }
    public void deleteSpecialty(String codeSpec, String codeKaf,String codeForm){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", codeSpec)
                .addValue("codeKaf", codeKaf)
                .addValue("codeForm", codeForm);
        deleteSpecByKafAndFormProc.execute(in);
        //jdbcTemplate.update("delete from kaf_spec_form where code_kaf= ? AND code_spec= ? AND code_form= ?",
                //codeKaf,codeSpec,codeForm);
    }
    private static final class SpecialtyMapper implements RowMapper<Specialty> {

        public Specialty mapRow(ResultSet rs, int rowNum) throws SQLException {
            String codeSpec = rs.getString("code");
            String name = rs.getString("name");
            String codeKaf = null;
            String qualification = null;
            String codeForm = null;
            try {codeKaf = rs.getString("codeKaf");
            }catch (SQLException e){ }
            try {qualification = rs.getString("qualification");
            }catch (SQLException e){ }
            try {codeForm = rs.getString("codeForm");
            }catch (SQLException e){ }
            Specialty specialty = new Specialty(codeSpec,name,codeKaf,codeForm,qualification);
            return specialty;
        }
    }

}
