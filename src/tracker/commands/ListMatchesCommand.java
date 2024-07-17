package tracker.commands;
import tracker.tournament.Match;
import tracker.tournament.Player;
import tracker.tournament.Team;
import java.util.HashMap;
import java.util.Map;

public class ListMatchesCommand implements CommandAPI {
    @Override
    public void execute(HashMap<String, Player> allPlayers, HashMap<String, Team> allTeams,
                        HashMap<Integer, Match> allMatches) {
        System.out.println("\n\n----------Complete schedule of matches----------\n");
        for(Map.Entry<Integer, Match> entry : allMatches.entrySet()){
            Match mch = entry.getValue();
            String builder = "\uD83D\uDCCC " +
                    mch.getDate().getMonth() + "/" + mch.getDate().getDayOfMonth() +
                    "/" + mch.getDate().getYear() + " (" + mch.getStatus() + ") "
                    + mch.getHomeTeam().getTeamName() + " - " + mch.getAwayTeam().getTeamName();

            System.out.println(builder);
        }
    }
}
