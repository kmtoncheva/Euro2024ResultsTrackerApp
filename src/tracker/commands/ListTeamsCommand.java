package tracker.commands;
import tracker.tournament.Match;
import tracker.tournament.Player;
import tracker.tournament.Team;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTeamsCommand implements CommandAPI {
    @Override
    public void execute(HashMap<String, Player> allPlayers, HashMap<String, Team> allTeams,
                        HashMap<Integer, Match> allMatches) {
        System.out.println("\n\n---------------Participating teams--------------\n");
        for(Map.Entry<String, Team> entry : allTeams.entrySet()){
            Team tm = entry.getValue();
            System.out.println("⚽ Team name: " + tm.getName());
            System.out.println("--Group: " + tm.getGroup());
            System.out.println("---Team coach: " + tm.getCoach());
            System.out.println("----Team members: ");

            List<String> idPlayers = tm.getTeamMembers();

            for(String curId : idPlayers){
                System.out.println("\uD83C\uDFC3\u200D♂\uFE0F" + allPlayers.get(curId).getName() +
                        " (" + allPlayers.get(curId).getPosition() + ")");
            }
            System.out.println("------------------------------------------------");
        }
    }
}
