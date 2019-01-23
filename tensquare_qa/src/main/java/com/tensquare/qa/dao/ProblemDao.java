package com.tensquare.qa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Modifying
    @Query(value = "SELECT * FROM tb_problem p LEFT JOIN tb_pl l ON p.id = l.problemid  LIMIT ?, ?", nativeQuery = true)
    List<Problem> getNewList(Integer page, Integer size);


    @Modifying
    @Query(value = "SELECT * FROM tb_problem p LEFT JOIN tb_pl l ON p.id = l.problemid where l.labelid = ? LIMIT ?, ?", nativeQuery = true)
    List<Problem> getNewListByLabelId(String label, Integer page, Integer size);
}
