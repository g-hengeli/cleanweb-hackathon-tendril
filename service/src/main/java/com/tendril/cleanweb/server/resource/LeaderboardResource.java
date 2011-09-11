package com.tendril.cleanweb.server.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tendril.cleanweb.domain.LeaderboardEntry;
import com.tendril.cleanweb.service.LeaderboardService;

@Path("/leaderboard")
@Produces(MediaType.APPLICATION_JSON)
public class LeaderboardResource {

	@Inject
	private LeaderboardService leaderboardService;

	@POST
	public void createLeaderboardEntry(@QueryParam("userId") String userId,
			@QueryParam("zipcode") String zipcode,
			@QueryParam("tariffName") String tariffName,
			@QueryParam("score") int score) {
		LeaderboardEntry entry = new LeaderboardEntry(userId, zipcode, score,
				tariffName);

		leaderboardService.persist(entry);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LeaderboardEntry> getLeaderboard() {
		return leaderboardService.getLeaderboard();
	}

	@POST
	@Path("/neighbors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LeaderboardEntry> getNeighbors(
			@QueryParam("userId") String userId,
			@QueryParam("zipcode") String zipcode,
			@QueryParam("tariffName") String tariffName,
			@QueryParam("score") int score) {
		LeaderboardEntry entry = new LeaderboardEntry(userId, zipcode, score,
				tariffName);

		return leaderboardService.getNeighbors(entry);
	}

}
