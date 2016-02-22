package com.none.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.none.pojo.Legislator;

public interface VoteMapper
{

	@Select("SELECT state FROM states ORDER BY state asc;")
	List<String> getStates();
	
	@Select("SELECT legislator as id,displayname as name FROM legislator WHERE state = #{state} ORDER BY displayname;")
	List<Legislator> getReps(@Param("state") String state);
	
	@Select("SELECT second as id, count, displayname as name, party, state from sm2015 join legislator2 on second = legislator where first = #{id} order by count desc limit 10")
	List<Legislator> getSims(@Param("id") String id);
	
	@Select("SELECT second as id, count, displayname as name, party, state from sm2015 join legislator2 on second = legislator where first = #{id} order by count asc limit 10")
	List<Legislator> getDims(@Param("id") String id);
	
	@Select("SELECT displayname as name, state, party FROM legislator2 WHERE legislator = #{id};")
	Legislator getDetail(@Param("id") String id);

}
