package com.hundsun.votesystem.service.impl;

import com.hundsun.votesystem.mapper.TstaffVoteMapper;
import com.hundsun.votesystem.mapper.VoteInfoMapper;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.TstaffVote;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sumimasah on 2018/10.
 */
import org.springframework.stereotype.Service;
@Service
public class VoteInfoServiceimpl implements VoteInfoService {
    @Autowired
    private VoteInfoMapper voteInfoMapper;
    @Autowired
    private TstaffVoteMapper staffVoteMapper;

    @Override
    public VoteInfo selectByPrimaryKey(Integer voteId) {
        VoteInfo voteInfo=voteInfoMapper.selectByPrimaryKey(voteId);
        return voteInfo;
    }

    @Override
    public List<HashMap<String,Integer>> getVoterNum(Integer voteInfoId) {
        List<HashMap<String,Integer>> voteNum=voteInfoMapper.getVoterNum(voteInfoId);
        return voteNum;
    }

    @Override
    public List<HashMap<String,Integer>> getVoteOptionNum(Integer voteId){
        List<HashMap<String,Integer>> list=voteInfoMapper.getVoterNum(voteId);
        return list;

    }

    
    /**
	 * @Title:updateStaffList
	 * @Description:更新投票人列表
	 * @param1 voteId
	 * @param2 selStaffList
	 * @param3 newStaffList
	 */
	@Override
	public String updateStaffList(Integer voteId,  List<StaffInfo> newStaffList) {
		
		staffVoteMapper.deleteByVoteId(voteId);
		for(StaffInfo staffInfo:newStaffList) {
			staffVoteMapper.insert(staffInfo.getStaffId(), voteId);	
		}
		return "更新成功";
	}

	@Override
	public String updateDepart(Integer voteId, Integer departId) {
		// TODO Auto-generated method stub
		return null;
	}
    
	

	


}
