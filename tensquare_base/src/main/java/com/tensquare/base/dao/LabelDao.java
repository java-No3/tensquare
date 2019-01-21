package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelDao extends JpaRepository<Label, String> {
}
