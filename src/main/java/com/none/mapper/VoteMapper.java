package com.none.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface VoteMapper
{

	@Select("SELECT state FROM states ORDER BY state asc;")
	List<String> getStates();
	
	@Select("SELECT id,displayname FROM legislator WHERE state = #{state};")
	List<String> getReps(@Param("state") String state);

}
