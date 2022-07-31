package com.zzw.dig_back_220721.dao.basicImgProcessDao;

import com.zzw.dig_back_220721.body.Para2;
import com.zzw.dig_back_220721.body.Para3;
import com.zzw.dig_back_220721.body.Para6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class B302Dao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Para2 GetResizeSub(String sid){
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

    public Para2 GetTranslationSub(String sid){
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

    public String GetMirrorFlipSub(String sid){
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

    public Para2 GetZoomSub(String sid){
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

    public Para6 GetWarpAffineSub(String sid){
        String sqlSta = "select para1,para2,para3,para4,para5,para6 from submission where sid ='"+sid+"' ;";
        Para6 p;
        try {
            p=jdbcTemplate.queryForObject(sqlSta,new BeanPropertyRowMapper<>(Para6.class));
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return p;
    }
}
