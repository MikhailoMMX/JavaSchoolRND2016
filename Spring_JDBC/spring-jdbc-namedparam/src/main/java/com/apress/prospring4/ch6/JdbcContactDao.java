package com.apress.prospring4.ch6;

import javax.sql.DataSource;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcContactDao implements ContactDao, InitializingBean {
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String findLastNameById(Long id) {
        String sql = "select last_name from contact where id = :contactId";

        Contact contact = new Contact();
        contact.setFirstName("first");
        contact.setLastName("last");
        contact.setBirthDate(new Date(System.currentTimeMillis()));
        SqlParameterSource test = new BeanPropertySqlParameterSource(contact);
        System.out.println(test);

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("contactId", id);
    
        return namedParameterJdbcTemplate.queryForObject(sql, 
            namedParameters, String.class); 
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = 
            new NamedParameterJdbcTemplate(dataSource);

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on ContactDao");
        }

        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("Null NamedParameterJdbcTemplate on ContactDao");
        }
    }
}
