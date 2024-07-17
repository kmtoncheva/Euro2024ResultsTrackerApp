package tracker.commands;
import tracker.tournament.Match;
import tracker.tournament.Player;
import tracker.tournament.Team;
import java.util.HashMap;

public interface CommandAPI {
    void execute(HashMap<String, Player> allPlayers, HashMap<String, Team> allTeams,
                 HashMap<Integer, Match> allMatches);
}
