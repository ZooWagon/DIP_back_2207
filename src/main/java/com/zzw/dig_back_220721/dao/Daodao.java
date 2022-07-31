package com.zzw.dig_back_220721.dao;

import com.zzw.dig_back_220721.body.ParaPacket;
import com.zzw.dig_back_220721.body.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Daodao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ParaPacket SelectResidentBySid(String sid) {
        String sqlSta = "select * from submission where sid='"
                + sid + "'";
        Map m;
        Submission s;
        List<Submission> ls;
        try {
            System.out.println("Do sql");
            m = jdbcTemplate.queryForMap(sqlSta);
            s = jdbcTemplate.queryForObject(sqlSta, new BeanPropertyRowMapper<>(Submission.class));
            ls=jdbcTemplate.query(sqlSta,new BeanPropertyRowMapper<>(Submission.class));
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
//        System.out.println(m);
//        s.show_test();
//        for (Submission item:ls){
//            item.show_test();
//        }
        return new ParaPacket();
    }
}
