package com.zzw.dig_back_220721.dao;

import com.zzw.dig_back_220721.body.ClockBody;
import com.zzw.dig_back_220721.body.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClockDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public ClockBody GetClockSubmissionBySid(String sid){
        String sqlSta = "select stime,para8 as iden_time from submission where sid ='"+sid+"' ;";
        ClockBody cb;
        try {
            cb=jdbcTemplate.queryForObject(sqlSta,new BeanPropertyRowMapper<>(ClockBody.class));
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return cb;
    }
}
