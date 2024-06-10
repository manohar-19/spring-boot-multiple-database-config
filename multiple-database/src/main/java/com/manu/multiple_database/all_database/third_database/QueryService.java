package com.manu.multiple_database.all_database.third_database;

import com.manu.multiple_database.all_database.second_database.second_model.SecondUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class QueryService {
    private final JdbcTemplate jdbcTemplate;

    public QueryService(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SecondUser> executeQuery(){
        return jdbcTemplate.query("select * from second_user", new UserRowMapper());
    }

    private static class UserRowMapper implements RowMapper<SecondUser> {

        @Override
        public SecondUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            SecondUser user = new SecondUser();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            return user;
        }


    }
}
