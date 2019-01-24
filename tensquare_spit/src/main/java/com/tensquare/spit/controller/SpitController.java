package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
