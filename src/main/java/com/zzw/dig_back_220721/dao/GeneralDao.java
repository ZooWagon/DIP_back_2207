package com.zzw.dig_back_220721.dao;

import com.zzw.dig_back_220721.body.Para2;
import com.zzw.dig_back_220721.body.Para3;
import com.zzw.dig_back_220721.body.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GeneralDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void InsertSubmission(Submission su){
        String sqlSta="insert into submission values " + su.getTuple();
        // System.out.println(sqlSta);
        try {
           jdbcTemplate.execute(sqlSta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Submission> GetSubmissionRecord(){
        String sqlSta = "select * from submission order by sid desc;";
        List<Submission> ls;
        try {
            ls=jdbcTemplate.query(sqlSta,new BeanPropertyRowMapper<>(Submission.class));
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return ls;
    }

    public String Get1ParaSub(String sid){
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

    public Para2 Get2ParaSub(String sid){
        String sqlSta = "select para1,para2 from submission where sid ='"+sid+"' ;";
        Para2 p;
        try {
            p=jdbcTemplate.queryForObject(sqlSta,new BeanPropertyRowMapper<>(Para2.class));
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return p;
    }

    public Para3 Get3ParaSub(String sid){
        String sqlSta = "select para1,para2,para3 from submission where sid ='"+sid+"' ;";
        Para3 p;
        try {
            p=jdbcTemplate.queryForObject(sqlSta,new BeanPropertyRowMapper<>(Para3.class));
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return p;
    }
}
