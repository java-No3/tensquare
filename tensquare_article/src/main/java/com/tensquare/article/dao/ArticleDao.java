package com.tensquare.article.dao;

import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 根据id更改article的state的值
     * @param id
     * @param state
     */
    @Modifying
    @Query("Update Article SET state = ?2 where id = ?1")
    int updateState(String id, String state);


    /**
     * 将thump自增 1, (点赞)
     * @param articleId
     */
    @Modifying
    @Query("Update Article SET thumbup = thumbup + 1 where id = ?1")
    void thumbUp(String articleId);

    /**
     * 获取isTop为1的文章列表
     * @return
     */
    @Modifying
    @Query(" FROM Article  where istop = '1'")
    List<Article> getTopArticle();
}
