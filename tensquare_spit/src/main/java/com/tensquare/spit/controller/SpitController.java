package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    /**
     * 增加吐槽
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addSpit(@RequestBody Spit spit){
        spitService.addSpit(spit);
        return new Result(true, StatusCode.OK,"增加成功");
    }


    /**
     * 查询所有吐槽列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result getAllSpit(){
        List<Spit> spitList = spitService.findAllSpit();
        return new Result(true, StatusCode.OK,"查询成功", spitList);
    }


    /**
     * 根据id查询吐槽
     * @return
     */
    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result getSpitById(@PathVariable String spitId){
        Spit spit = spitService.findSpitById(spitId);
        return new Result(true, StatusCode.OK,"查询成功", spit);
    }


    /**
     * 根据id修改
     * @param spitId
     * @param spit
     * @return
     */
    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result updateSpitById(@PathVariable String spitId, @RequestBody Spit spit){
        spitService.updateSpitById(spitId, spit);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    /**
     * 根据id删除
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result deleteSpitById(@PathVariable String spitId){
        spitService.deleteSpitById(spitId);
        return new Result(true, StatusCode.OK,"删除成功");
    }


    /**
     * 吐槽点赞功能
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result thumbupSpitById(@PathVariable String spitId){
        // 1.获取用户id  todo 暂时用固定用户名代替
        String UserName = "jason";
        // 2.判断用户是否已经点赞
            // 2.1 已经点赞 取消点赞
            // 2.2 未点赞
        spitService.thumbupSpitById(spitId);
        return new Result(true, StatusCode.OK,"点赞成功");
    }

}
