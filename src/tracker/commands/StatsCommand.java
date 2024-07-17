package tracker.commands;
import tracker.tournament.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StatsCommand implements CommandAPI{
    private final Map<String, Player> allPlayers;
    private final Map<String, Team> allTeams;

    public StatsCommand(Map<String, Player> allPlayers, Map<String, Team> allTeams){
        this.allPlayers = allPlayers;
        this.allTeams = allTeams;
    }

    @Override
    public void execute() {
        PriorityQueue<TeamEntity> pq = new PriorityQueue<>();
        for (Map.Entry<String, Player> entry : allPlayers.entrySet()){
            Player pl = entry.getValue();
            if(pl.getTotalGoals() > 0){
                TeamEntity te = new TeamEntity(pl.getName(), pl.getTotalGoals());
                pq.add(te);
            }
        }

        System.out.println("\n\n----------------------Stats---------------------");
        System.out.println("\n\uD83E\uDD45Total Goals : " + Tournament.getInstance().getTotalGoals());
        System.out.println("\n----------------------Teams---------------------\n");
        for(Map.Entry<String, Team> entry : allTeams.entrySet()){
            Team team = entry.getValue();
            System.out.println("⚽ " + team.getName() + " : " + team.getTotalGoals());
        }
        System.out.println("\n----------------------Top 3---------------------\n");
        for(int i = 0; i < 3; i++){
            TeamEntity teamEntity = pq.poll();
            assert teamEntity != null;
            System.out.println("\uD83C\uDFC5 " + teamEntity.getTeamName()
                    + " : "  + teamEntity.getScore());
        }

        System.out.println("\n-----------------------All----------------------");
        boolean flagScorers = false;
        for (Map.Entry<String, Team> entry : allTeams.entrySet()){
           System.out.println("⚽ " + entry.getValue().getName());
            for(String plId : entry.getValue().getTeamMembers()){
               if(allPlayers.get(plId).getTotalGoals() > 0){
                   flagScorers = true;
                   System.out.println(" -" + allPlayers.get(plId).getName() +
                           " : " +  allPlayers.get(plId).getTotalGoals());
               }
            }
            if(!flagScorers) {
                System.out.println("❌");
            }
        }

        System.out.println("\n----------------------Cards---------------------\n");
        System.out.println("\uD83C\uDFF3\uFE0F : " + Tournament.getInstance().getTotalCardsYellow());
        System.out.println("\uD83D\uDEA9 : " + Tournament.getInstance().getTotalCardsRed() + "\n");
        for (Map.Entry<String, Team> entry : allTeams.entrySet()){
            System.out.println("⚽ " + entry.getValue().getName() + " : "
             + entry.getValue().getTotalCardsYellow() + " \uD83C\uDFF3\uFE0F "
            + entry.getValue().getTotalCardsRed() + " \uD83D\uDEA9");
        }
    }
}
