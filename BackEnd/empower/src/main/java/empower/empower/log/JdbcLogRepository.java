package empower.empower.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcLogRepository implements LogRepository{
    private JdbcTemplate jdbcTemplate;

    public JdbcLogRepository(JdbcTemplate template){
        this.jdbcTemplate = template;
    }

    @Override
    public Long save(Log log) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement statement = conn.prepareStatement("insert into logging (itemName) values (?) ", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, log.getItemName());
                return statement;
            }}, holder);

        Long primaryKey = holder.getKey().longValue();
        return primaryKey;
    }

    @Override
    public int editLog(Log log) {
        return jdbcTemplate.update(
                "update logging set itemName = ?, itemNotes = ? where id = ?", log.getItemName(), log.getItemNotes(), log.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete logging where id = ?", id);
    }

   
    @Override
    public List<Log> findAll() {
        List<Log> all= jdbcTemplate.query("select * logging", 
        (rs, rowNum)-> new Log( rs.getLong("id"), rs.getString("itemName") ) );

        return all;
    }
    
    @Override
    public Optional<Log> findById(Long id) {
        try{
            return jdbcTemplate.queryForObject("select * from logging where id = ?",
            (rs, rowNum) -> Optional.of(new Log(rs.getLong("id"), rs.getString("itemName"))), 
            new Object[]{id});

        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
