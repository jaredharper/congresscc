package com.none.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.none.pojo.Legislator;
import com.none.pojo.LegislatorSummary;

/**
 * Spring/Mybatis mapper class that holds SQL queries
 * 
 * @author jthomas
 *
 */
public interface VoteMapper
{

	// Get all states
	@Select("SELECT state FROM states ORDER BY state asc;")
	List<String> getStates();
	
	// Get all legislators for the given state
	@Select("SELECT legislator as id,displayname as name FROM legislator3 WHERE state = #{state} ORDER BY displayname;")
	List<Legislator> getReps(@Param("state") String state);
	
	// Get a list of Legislators that voted most like this one (id)
	@Select("SELECT second as id, count, displayname as name, party, state from sm2015 join legislator3 on second = legislator where first = #{id} order by count desc limit 6")
	List<Legislator> getSims(@Param("id") String id);
	
	// Get a list of Legislators that voted least like this one (id)
	@Select("SELECT second as id, count, displayname as name, party, state from sm2015 join legislator3 on second = legislator where first = #{id} order by count asc limit 6")
	List<Legislator> getDims(@Param("id") String id);
	
	// Get the 2016 Presidential candidates and compare them to this one (id)
	@Select("SELECT second as id, count, displayname as name, party, state from sm2015 join legislator3 on second = legislator where first = #{id} and (legislator = 'S350' or legislator = 'S355' or legislator = 'S348' or legislator = 'S366'or legislator = 'S313') order by count asc limit 6;")
	List<Legislator> getCandidates(@Param("id") String id);
	
	// Get a specific legislator's detailed info
	@Select("SELECT displayname as name, state, party FROM legislator3 WHERE legislator = #{id};")
	Legislator getDetail(@Param("id") String id);
	
	// Get a specific legislator's summary info
	@Select("SELECT success,demvotes,repvotes,bivote FROM cc2015repsummary WHERE id = #{id};")
	LegislatorSummary getSummary(@Param("id") String id);
	
	// Get the n most successful legislators
	@Select("SELECT id,success as count,displayname as name,party,state FROM cc2015repsummary,legislator3 WHERE id = legislator ORDER BY success DESC LIMIT 3;")
	List<Legislator> getTopThreeSuccess();
	
	// Get the GOP legislators that voted most with the Dems 
	@Select("SELECT id,demvotes as count,displayname as name,party,state FROM cc2015repsummary,legislator3 WHERE id = legislator AND party = 'R' ORDER BY demvotes DESC LIMIT 3;")
	List<Legislator> getTopThreeRep();
	
	// Get the Dems that voted most with the GOP
	@Select("SELECT id,repvotes as count,displayname as name,party,state FROM cc2015repsummary,legislator3 WHERE id = legislator AND party = 'D' ORDER BY repvotes DESC LIMIT 3;")
	List<Legislator> getTopThreeDem();
	
	// Hit counter for legislators' detailed info
	@Update("UPDATE cc2015count SET count = count + 1 WHERE id = #{id}")
	void updateCount(@Param("id") String id);

}
