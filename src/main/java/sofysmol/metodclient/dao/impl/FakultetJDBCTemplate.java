package sofysmol.metodclient.dao.impl;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.data.Fakultet;
import sofysmol.metodclient.dao.interf.FakultetDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by sofysmo on 08.10.16.
 */
@Repository
public class FakultetJDBCTemplate implements FakultetDao {
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
                        new FakultetMapper());
        getFaksProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getFakultets")
                .returningResultSet("fakultets",
                        new FakultetMapper());
        updateFakProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("updateFakultet");
        deleteFakProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("deleteFakultet");
        insertFakProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insertFakultet");

    }
    public Fakultet getFakultet(String code) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code);
        Map m = getFakProc.execute(in);
        return ((List<Fakultet>) m.get("fakultet")).get(0);
    }
    public List<Fakultet> getFakultets(){
        Map m = getFaksProc.execute();
        return ((List<Fakultet>) m.get("fakultets"));
    }
    public void updateFakultet(Fakultet fakultet) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", fakultet.getCode())
                .addValue("name", fakultet.getName());
        updateFakProc.execute(in);
    }

    public void deleteFakultet(String code){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code);
        deleteFakProc.execute(in);
    }

    public void insertFakultet(Fakultet fakultet){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", fakultet.getCode())
                .addValue("name", fakultet.getName());
        insertFakProc.execute(in);
    }
    private static final class FakultetMapper implements RowMapper<Fakultet> {

        public Fakultet mapRow(ResultSet rs, int rowNum) throws SQLException {
            Fakultet Fakultet = new Fakultet(rs.getString("code"),
                    rs.getString("name"));
            return Fakultet;
        }
    }

}
