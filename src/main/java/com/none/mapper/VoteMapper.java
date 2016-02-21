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
	
	@Select("SELECT second as id, count from sm2015 where first = #{id} order by count desc limit 10")
	List<Legislator> getSims(@Param("id") String id);
	
	@Select("SELECT second as id, count from sm2015 where first = #{id} order by count asc limit 10")
	List<Legislator> getDims(@Param("id") String id);

}
