package com.zzw.dig_back_220721.dao.basicImgProcessDao;

import com.zzw.dig_back_220721.body.ClockBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class B301Dao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String GetThresholdBySid(String sid){
        String sqlSta = "select para1 from submission where sid ='"+sid+"' ;";
        String t;
        try {
            t=jdbcTemplate.queryForObject(sqlSta,String.class);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }
}
