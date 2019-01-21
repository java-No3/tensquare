package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import java.util.List;

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 保存或修改
     *
     * @param label
     */
    public void addOrUpdateLabel(Label label) {
        if (StringUtils.isEmpty(label.getId())) {
            // 前端无id 是保存操作,设置id
            label.setId(idWorker.nextId() + "");
        }
        labelDao.save(label);
    }

    /**
     * 按照id删除标签
     *
     * @param labelID
     */
    public void delLabel(String labelID) {
        labelDao.deleteById(labelID);
    }

    /**
     * 按照id查询标签
     *
     * @param labelID
     * @return
     */
    public Label findLabelById(String labelID) {
        return labelDao.findById(labelID).get();
    }

    /**
     * 查询所有标签
     *
     * @return
     */
    public List<Label> findAllLabel() {
        // 演示异常处理
        // int i = 1 / 0;
        return labelDao.findAll();
    }

}
