package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 查询所有吐槽
     * @return
     */
    public List<Spit> findAllSpit() {
        return spitDao.findAll();
    }

    /**
     * 根据id查询
     * @param spitId
     * @return
     */
    public Spit findSpitById(String spitId) {
        return spitDao.findById(spitId).get();
    }

    /**
     * 根据id修改
     * @param spitId
     * @param spit
     */
    public void updateSpitById(String spitId, Spit spit) {
        spit.set_id(spitId);
        spitDao.save(spit);
    }

    /**
     * 根据id删除
     * @param spitId
     */
    public void deleteSpitById(String spitId) {
        spitDao.deleteById(spitId);
    }

    /**
     * 根据id点赞
     * @param spitId
     */
    public void thumbupSpitById(String spitId) {

    }
}
