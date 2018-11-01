package com.hundsun.votesystem.controller;

import com.google.gson.Gson;
import com.hundsun.votesystem.model.ReturnData;
import com.hundsun.votesystem.model.StaffInfo;
import com.hundsun.votesystem.model.VoteInfo;
import com.hundsun.votesystem.service.VoteInfoService;
import com.hundsun.votesystem.util.ThreadVote;

import com.hundsun.votesystem.util.VoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("voteinfo")
public class VoteInfoController {

	@Autowired
	private VoteInfoService voteInfoService;

	//通过voteInfoId获取投票详情
	@RequestMapping("finalvoteinfo")
	public String test(HttpServletRequest request, HttpServletResponse response) {
		VoteUtils.kuayuSolution(request, response);
		int voteInfoId = Integer.parseInt(request.getParameter("voteInfoId"));
		HashMap<String, Object> totalResult = null;
		HashMap<String, Integer> voterNum = new HashMap<>();
		ReturnData returnData = new ReturnData();
		Integer state = 1;
		try {
			totalResult = new HashMap<>();
			VoteInfo voteInfo = voteInfoService.selectByPrimaryKey(voteInfoId);
			List<HashMap<String, Integer>> num = voteInfoService.getVoterNum(voteInfoId);
			List<HashMap<String, Integer>> optionNum = voteInfoService.getVoteOptionNum(voteInfoId);
			if (num.size() == 2) {
				voterNum.put("hasvote", num.get(0).get("voterNum"));
				voterNum.put("novote", num.get(1).get("voterNum"));
			} else if (num.size() == 1) {
				if (state.equals(num.get(0).get("voteState"))) {
					voterNum.put("hasvote", num.get(0).get("voterNum"));
					voterNum.put("nosvote", 0);
				} else {
					voterNum.put("novote", num.get(0).get("voterNum"));
					voterNum.put("hasvote", 0);
				}
			}else {
				voterNum.put("hasvote", 0);
				voterNum.put("nosvote", 0);
			}

			totalResult.put("voteInfo", voteInfo);
			totalResult.put("voternum", voterNum);
			totalResult.put("optionNum", optionNum);
			returnData.setReturnObject(totalResult);
		} catch (Exception e) {
			returnData.setReturnMsg("error");
			returnData.setReturnMsgDetail(e.getMessage());

		}
		return new Gson().toJson(returnData);
	}


}
