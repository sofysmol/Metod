package sofysmol.metodclient.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import sofysmol.metodclient.dao.interf.KafedraDao;
import sofysmol.metodclient.data.Faculty;
import sofysmol.metodclient.data.Kafedra;
import sofysmol.metodclient.data.Kafedra;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by sofysmo on 08.10.16.
 */
@Repository
public class KafedraJDBCTemplate implements KafedraDao {
    private DataSource dataSource;
    private SimpleJdbcCall getKafProc;
    private SimpleJdbcCall getKafsProc;
    private SimpleJdbcCall updateKafProc;
    private SimpleJdbcCall deleteKafProc;
    private SimpleJdbcCall insertKafProc;
    private SimpleJdbcCall getKafsByFakProc;
    
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        getKafProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getKafedra")
                .returningResultSet("kafedra",
                        new KafedraMapper());
        getKafsProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getKafedras")
                .returningResultSet("kafedras",
                        new KafedraMapper());
        getKafsByFakProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getKafedrasByFak")
                .returningResultSet("kafedras",
                        new KafedraMapper());
        updateKafProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("updateKafedra");
        deleteKafProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("deleteKafedra");
        insertKafProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insertKafedra");
    }
    public Kafedra getKafedra(String code) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code);
        Map m = getKafProc.execute(in);
        return ((List<Kafedra>) m.get("kafedra")).get(0);
    }

    public List<Kafedra> getKafedras(){
        Map m = getKafsProc.execute();
        return ((List<Kafedra>) m.get("kafedras"));
    }

    public List<Kafedra> getKafedrasByFakultet(String code){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code);
        Map m = getKafsByFakProc.execute(in);
        return ((List<Kafedra>) m.get("kafedras"));
    }

    public void updateKafedra(Kafedra kafedra)
    {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", kafedra.getCode())
                .addValue("name", kafedra.getName())
                .addValue("phone", kafedra.getPhone())
                .addValue("codeFak", kafedra.getCodeFak());
        updateKafProc.execute(in);
    }

    public void deleteKafedra(String code){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", code);
        deleteKafProc.execute(in);
    }

    public void insertKafedra(Kafedra kafedra){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("code", kafedra.getCode())
                .addValue("name", kafedra.getName())
                .addValue("phone", kafedra.getPhone())
                .addValue("codeFak", kafedra.getCodeFak());
        insertKafProc.execute(in);
    }

    private static final class KafedraMapper implements RowMapper<Kafedra> {

        public Kafedra mapRow(ResultSet rs, int rowNum) throws SQLException {
            Kafedra kafedra = new Kafedra(rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("codeFak"));

            return kafedra;
        }
    }


}
