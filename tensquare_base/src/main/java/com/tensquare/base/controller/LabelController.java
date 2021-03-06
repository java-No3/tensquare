package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
@CrossOrigin // 允许跨域访问
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 添加标签
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addLabel(@RequestBody Label label){
        labelService.addOrUpdateLabel(label);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    /**
     * 查询所有标签
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAllLabel(){
        List<Label> labelList = labelService.findAllLabel();
        return new Result(true, StatusCode.OK,"查询成功",labelList);
    }

    /**
     * 通过id查询标签
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findLabelById(@PathVariable String labelId){
        Label label = labelService.findLabelById(labelId);
        return new Result(true, StatusCode.OK,"查询成功",label);
    }


    /**
     * 根据id删除标签
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result delLabelById(@PathVariable String labelId){
        labelService.delLabel(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }


    /**
     * 修改标签
     * @param labelId
     * @param label
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result UpdateLabelById(@PathVariable String labelId, @RequestBody Label label){
        label.setId(labelId);
        labelService.addOrUpdateLabel(label);
        return new Result(true, StatusCode.OK,"修改成功");
    }


    /**
     * 按照条件查询
     * @param label
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch( @RequestBody Label label){
        List<Label> search = labelService.findSearch(label);
        return new Result(true, StatusCode.OK,"查询成功",search);
    }


    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearchPage(@RequestBody Label label, @PathVariable Integer page, @PathVariable Integer size){
        Page<Label> pages = labelService.findSearchPage(label, page, size);
        Long totalPages = pages.getTotalElements();
        List<Label> labelList = pages.getContent();
        PageResult pageResult = new PageResult(totalPages, labelList);
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }
}
