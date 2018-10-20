package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("votesys")
public class MyVoteController {
    @Autowired
    private VoteServiceImpl voteServiceBase;
    //通过id搜索投票
    @RequestMapping("getmyvote")
    public String getStaffById(int staffid){
        return new Gson().toJson(voteServiceBase.getClassificationVoteInfo(staffid));
    }
}
