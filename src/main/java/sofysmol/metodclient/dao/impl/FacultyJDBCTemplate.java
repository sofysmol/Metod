package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.data.Faculty;
import sofysmol.metodclient.dao.interf.FacultyDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by sofysmo on 08.10.16.
 */
@Repository
public class FacultyJDBCTemplate implements FacultyDao {
    private DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getFakProc;
    private SimpleJdbcCall getFaksProc;
    private SimpleJdbcCall updateFakProc;
    private SimpleJdbcCall deleteFakProc;
    private SimpleJdbcCall insertFakProc;
    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        getFakProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getFakultet")
                .returningResultSet("fakultet",
                        new FacultyMapper());
        getFaksProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getFakultets")
                .returningResultSet("fakultets",
                        new FacultyMapper());
        updateFakProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("updateFakultet");
        deleteFakProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("deleteFakultet");
        insertFakProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insertFakultet");

    }
    public Faculty getFaculty(String code) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code);
        Map m = getFakProc.execute(in);
        return ((List<Faculty>) m.get("fakultet")).get(0);
    }
    public List<Faculty> getFaculties(){
        Map m = getFaksProc.execute();
        return ((List<Faculty>) m.get("fakultets"));
    }
    public void updateFaculty(Faculty faculty) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", faculty.getCode())
                .addValue("name", faculty.getName());
        updateFakProc.execute(in);
    }

    public void deleteFaculty(String code){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code);
        deleteFakProc.execute(in);
    }

    public void insertFaculty(Faculty faculty){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", faculty.getCode())
                .addValue("name", faculty.getName());
        insertFakProc.execute(in);
    }
    private static final class FacultyMapper implements RowMapper<Faculty> {

        public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {
            Faculty Faculty = new Faculty(rs.getString("code"),
                    rs.getString("name"));
            return Faculty;
        }
    }

}