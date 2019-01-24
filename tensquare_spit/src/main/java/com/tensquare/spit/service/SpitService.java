package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    /**
     * 添加吐槽
     * @param spit
     */
    public void addSpit(Spit spit) {
        spitDao.save(spit);
    }
}
