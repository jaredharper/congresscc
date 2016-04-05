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

	// Get years we have data for
	@Select("SELECT year FROM ccvalidyears ORDER BY year desc;")
	List<String> getYears();
	
	// Get all legislators for the given state
	@Select("SELECT legislator as id,displayname as name FROM cc${year}legislator WHERE state = #{state} ORDER BY displayname;")
	List<Legislator> getReps(@Param("state") String state, @Param("year") Integer year);
	
	// Get a list of Legislators that voted most like this one (id)
	@Select("SELECT second as id, count, displayname as name, party, state from cc${year}similarity join cc${year}legislator on second = legislator where first = #{id} order by count desc limit 6")
	List<Legislator> getSims(@Param("id") String id, @Param("year") Integer year);

	@Select("SELECT first as id, count, displayname as name, party, state from cc${year}similarity join cc${year}legislator on first = legislator where second = #{id} order by count desc limit 6")
	List<Legislator> getSimsReverse(@Param("id") String id, @Param("year") Integer year);
	
	// Get a list of Legislators that voted least like this one (id)
	@Select("SELECT second as id, count, displayname as name, party, state from cc${year}similarity join cc${year}legislator on second = legislator where first = #{id} order by count asc limit 6")
	List<Legislator> getDims(@Param("id") String id, @Param("year") Integer year);

	@Select("SELECT first as id, count, displayname as name, party, state from cc${year}similarity join cc${year}legislator on first = legislator where second = #{id} order by count asc limit 6")
	List<Legislator> getDimsReverse(@Param("id") String id, @Param("year") Integer year);

	// Get the 2016 Presidential candidates and compare them to this one (id)
	@Select("SELECT second as id, count, displayname as name, party, state from cc${year}similarity join cc${year}legislator on second = legislator where first = #{id} and (legislator = 'S350' or legislator = 'S355' or legislator = 'S348' or legislator = 'S366'or legislator = 'S313') order by count asc limit 6;")
	List<Legislator> getCandidates(@Param("id") String id, @Param("year") Integer year);
	
	// Get a specific legislator's detailed info
	@Select("SELECT displayname as name, state, party FROM cc${year}legislator WHERE legislator = #{id};")
	Legislator getDetail(@Param("id") String id, @Param("year") Integer year);
	
	// Get a specific legislator's summary info
	@Select("SELECT success,demvotes,repvotes,bivotes as bivote FROM cc${year}legislator WHERE legislator = #{id};")
	LegislatorSummary getSummary(@Param("id") String id, @Param("year") Integer year);
	
	// Get the n most successful legislators
	@Select("SELECT legislator as id,success as count,displayname as name,party,state FROM cc${year}legislator ORDER BY success DESC LIMIT 3;")
	List<Legislator> getTopThreeSuccess(@Param("year") Integer year);
	
	// Get the GOP legislators that voted most with the Dems 
	@Select("SELECT legislator as id,demvotes as count,displayname as name,party,state FROM cc${year}legislator WHERE party = 'R' ORDER BY demvotes DESC LIMIT 3;")
	List<Legislator> getTopThreeRep(@Param("year") Integer year);
	
	// Get the Dems that voted most with the GOP
	@Select("SELECT legislator as id,repvotes as count,displayname as name,party,state FROM cc${year}legislator WHERE party = 'D' ORDER BY repvotes DESC LIMIT 3;")
	List<Legislator> getTopThreeDem(@Param("year") Integer year);

	// Get the bills the specified Democrat broke ranks on
	@Select("select id from v${year} join cc${year}total on legislation = id where legislator = #{id} and (((vote = 'y') and (ry > rn) and (dy < dn)) or ((vote = 'n') and (rn > ry) and (dn < dy))) order by id limit 100;")
	List<String> getDemDeviation(@Param("id") String id, @Param("year") Integer year);
	
	// Get the bills the specified Republican broke ranks on
	@Select("select  * from v${year} join cc${year}total on legislation = id where legislator = #{id} and (((vote = 'y') and (ry < rn) and (dy > dn)) or ((vote = 'n') and (rn < ry) and (dn > dy))) order by id limit 100;")
	List<String> getRepDeviation(@Param("id") String id, @Param("year") Integer year);
	
	// Hit counter for legislators' detailed info
	@Update("UPDATE cc2015count SET count = count + 1 WHERE id = #{id}")
	void updateCount(@Param("id") String id);

}
