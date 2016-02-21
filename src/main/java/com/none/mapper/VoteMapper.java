package com.none.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.none.pojo.Legislator;

public interface VoteMapper
{

	@Select("SELECT state FROM states ORDER BY state asc;")
	List<String> getStates();
	
	@Select("SELECT legislator as id,displayname as name FROM legislator WHERE state = #{state};")
	List<Legislator> getReps(@Param("state") String state);

}
