package com.hundsun.votesystem.service;

import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
/**
 * Created by sumimasah on 2018/10.
 */
@Service
public interface VoteInfoService {
    //获取投票基本信息
    VoteInfo selectByPrimaryKey(Integer voteId);
    //获取投票参与人数
    List<HashMap<String,Integer>> getVoterNum(Integer voteInfoId);

    List<HashMap<String,Integer>> getVoteOptionNum(Integer voteId);


      
    //更新投票参与员工列表
    String updateStaffList(Integer voteId,  List<StaffInfo> newStaffList);
    //更新投票参与部门
    String updateDepart(Integer voteId, Integer departId );
    
    
    
}
