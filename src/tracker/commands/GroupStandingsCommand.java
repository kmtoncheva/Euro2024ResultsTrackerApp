package tracker.commands;
import tracker.tournament.*;

import java.util.*;

public class GroupStandingsCommand implements CommandAPI {
    @Override
    public void execute(HashMap<String, Player> allPlayers, HashMap<String, Team> allTeams,
                        HashMap<Integer, Match> allMatches) {
        System.out.println("\n\n-------------Current group standings------------\n");
        Map<Group, List<Team>> groups = new TreeMap<>();

        for (Map.Entry<String, Team> entry : allTeams.entrySet()) {
            Team curTeam = entry.getValue();
            if (!groups.containsKey(curTeam.getGroup())) {
                groups.put(curTeam.getGroup(), new ArrayList<>());
            }
            groups.get(curTeam.getGroup()).add(curTeam);
        }
        groups.forEach((key, value) -> {
            value.sort(Comparator.comparing(Team::getName));
        });

        for (Map.Entry<Group, List<Team>> entry : groups.entrySet()) {
            Group curGroup = entry.getKey();
            System.out.println("Group " + curGroup.toString() + "\n");
            StringBuilder builder = new StringBuilder();
            for (Team team : entry.getValue()) {
                builder.append("⚽ ").append(team.getName()).append(" | ");
                GroupStanding groupStanding = new GroupStanding();
                groupStanding.calculateStanding(team.getName(), allMatches);

                builder.append(groupStanding.getPlayedMatches()).append(" played matches | ")
                        .append(groupStanding.getWins()).append(" wins | ").append(groupStanding.getLosses())
                        .append(" losses | ").append(groupStanding.getDraws()).append(" draws | ")
                        .append(groupStanding.calculatePoints()).append(" points\n");
            }
            System.out.println(builder);
        }
    }
}
