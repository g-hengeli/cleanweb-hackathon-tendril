package com.tendril.cleanweb.server.resource;

import com.tendril.cleanweb.domain.LeaderboardEntry;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.logging.Logger;

/**
 */
@Path("leaderboard")
public class LeaderboardResource {
    static Logger log = Logger.getLogger(LeaderboardResource.class.getName());

    @POST
    public void createLeaderboard(LeaderboardEntry entry) {
        log.info(entry.getZipcode() + " " + entry.getTariffName() + " " + entry.getScore());
    }
}
