package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.returndata.VoteInfoWithStaffNum;
import com.hundsun.votesystem.service.impl.VoteServiceImpl;
import com.hundsun.votesystem.util.VoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("votesys")
public class MyVoteController {
    @Autowired
    private VoteServiceImpl voteServiceBase;
    //通过id搜索投票
    @RequestMapping("getmyvote")
    public String getStaffById(HttpServletRequest request, HttpServletResponse response){
        VoteUtils.kuayuSolution(request,response);
        int staffid= Integer.parseInt(request.getParameter("staffid"));
        ReturnData returnData=new ReturnData();
        try{
            Map<String, List<VoteInfoWithStaffNum>> resultMap=voteServiceBase.getClassificationVoteInfo(staffid);
            returnData.setReturnObject(resultMap);
        }catch (Exception e){
            returnData.setReturnMsg("error");
            returnData.setReturnMsgDetail(e.getMessage());
        }
        return new Gson().toJson(returnData);
    }
}
