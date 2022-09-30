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
        // Use KeyHolder to obtain the auto-generated key from the "insert" statement
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement statement = conn.prepareStatement("insert into log (itemName) values (?) ", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, log.getItemName());
                return statement;
            }}, holder);

        Long primaryKey = holder.getKey().longValue();
        return primaryKey;
    }

    @Override
    public int editLog(Log log) {
        return jdbcTemplate.update(
                "update logs set itemName = ?, itemNotes = ? where id = ?", log.getItemName(), log.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete logs where id = ?", id);
    }

    /**
     * TODO: Activity 1 - Add code to return all books
     * Hint: use the "query" method of JdbcTemplate
     * Refer to the below code of "findByID" method on how to implement a RowMapper using a lambda expression
     * 
     */
    @Override
    public List<Log> findAll() {
        List<Log> all= jdbcTemplate.query("select * logs", 
        (rs, rowNum)-> new Log( rs.getLong("id"), rs.getString("itemName") ) );

        return all;
    }
    
    @Override
    public Optional<Log> findById(Long id) {
        try{
            return jdbcTemplate.queryForObject("select * from logs where id = ?",
            // implement RowMapper interface to return the book found
            // using a lambda expression
            (rs, rowNum) -> Optional.of(new Log(rs.getLong("id"), rs.getString("itemName"))), 
            new Object[]{id});

        }catch(EmptyResultDataAccessException e){
            // book not found - return an empty object
            return Optional.empty();
        }
    }
}
