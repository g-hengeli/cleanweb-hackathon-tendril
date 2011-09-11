package com.tendril.cleanweb.server.resource;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

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
	static Logger log = Logger.getLogger(LeaderboardResource.class.getName());

	@Inject
	private LeaderboardService leaderboardService;

	// @POST
	// @Consumes(MediaType.APPLICATION_JSON)
	// public void createLeaderboardEntry(LeaderboardEntry entry) throws
	// JSONException {
	// log.info(entry.getZipcode() + " " + entry.getTariffName() + " " +
	// entry.getScore());
	// // log.info(entry);
	// }

	@POST
	public void createLeaderboardEntry(@QueryParam("userId") String userId,
			@QueryParam("zipcode") String zipcode,
			@QueryParam("tariffName") String tariffName,
			@QueryParam("score") int score) {
		LeaderboardEntry entry = new LeaderboardEntry(userId, zipcode, score,
				tariffName);
		log.info(entry.toString());
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LeaderboardEntry> getLeaderboard() {
		return tempLeaderboard;
	}

	static List<LeaderboardEntry> tempLeaderboard = Arrays.asList(
			new LeaderboardEntry("1", "12345", 1, "super awesome"),
			new LeaderboardEntry("2", "98765", 1000, "not so awesome"));
}
