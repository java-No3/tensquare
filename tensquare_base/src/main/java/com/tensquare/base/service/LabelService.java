package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        //int i = 1 / 0;
        return labelDao.findAll();
    }

    /**
     * 条件查询
     * {
     * "id": "string",
     * "labelname": "string",
     * "state": "string",
     * "count": 0,
     * "recommend": "string"
     * }
     *
     * @return
     */
    public List<Label> findSearch(Label label) {
        // 使用Predicate
       /* List<Label> labelList = labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (!StringUtils.isEmpty(label.getId())) {
                    Predicate p1 = cb.equal(root.get("id").as(String.class), label.getId());
                    predicateList.add(p1);
                }
                if (!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate p2 = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(p2);
                }
                if (!StringUtils.isEmpty(label.getState())) {
                    Predicate p3 = cb.equal(root.get("state").as(String.class), label.getState());
                    predicateList.add(p3);
                }
                if (label.getCount() != null) {
                    Predicate p4 = cb.equal(root.get("count").as(Integer.class), label.getCount());
                    predicateList.add(p4);
                }
                if (!StringUtils.isEmpty(label.getRecommend())) {
                    Predicate p5 = cb.equal(root.get("recommend").as(String.class), label.getRecommend());
                    predicateList.add(p5);
                }
                Predicate predicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
                return predicate;
            }

        });
        return labelList;*/
        // 写法二:  lambda表达式写法
      /*  labelDao.findAll((root,query,cb)->{
            // 判断+拼接条件
            if (StringUtils.isEmpty(label.getId())){
                cb.equal(root.get("id").as(String.class),label.getId());
            }
            return null;
        });*/

        // 写法三 :使用Example查询 传入对象,根据对象的条件自动判断条件
            // 需要使用模糊查询
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("labelname", m -> m.contains());
        return labelDao.findAll(Example.of(label,matcher));
    }


    /**
     * 条件查询+分页
     * @param label 条件
     * @param page 当前页码
     * @param size 每页显示条数
     * @return
     */
    public Page<Label> findSearchPage(Label label, Integer page, Integer size) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("labelname", m -> m.contains());
        PageRequest pageRequest = new PageRequest(page -1, size);
        return labelDao.findAll(Example.of(label,matcher), pageRequest);
    }
}
