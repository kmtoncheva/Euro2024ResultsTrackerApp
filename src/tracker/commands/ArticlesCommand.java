package tracker.commands;
import tracker.tournament.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ArticlesCommand implements CommandAPI{
    private boolean equalResult = false;
    private TeamEntity winnerTeam;
    private TeamEntity defeatedTeam;

    public ArticlesCommand(){
        winnerTeam = new TeamEntity();
        defeatedTeam = new TeamEntity();
    }
    private void setDefeatedTeam(TeamEntity defeatedTeam) {this.defeatedTeam = defeatedTeam;}
    private void setWinnerTeam(TeamEntity winnerTeam) {this.winnerTeam = winnerTeam;}
    private void setTeams(Match match){
        if(match.getHomeTeam().getScore() > match.getAwayTeam().getScore()){
            setWinnerTeam(match.getHomeTeam());
            setDefeatedTeam(match.getAwayTeam());
        }
        else if(match.getHomeTeam().getScore() < match.getAwayTeam().getScore()){
            setWinnerTeam(match.getAwayTeam());
            setDefeatedTeam(match.getHomeTeam());
        }
        else{
            equalResult = true;
            setWinnerTeam(match.getHomeTeam());
            setDefeatedTeam(match.getAwayTeam());
        }
    }
    public TeamEntity getDefeatedTeam() {return defeatedTeam;}
    public boolean isEqualResult() {return equalResult;}
    public TeamEntity getWinnerTeam() {return winnerTeam;}

    @Override
    public void execute(HashMap<String, Player> allPlayers, HashMap<String, Team> allTeams,
                        HashMap<Integer, Match> allMatches) {

        Queue<Match> pq = new PriorityQueue<>();
        for (Map.Entry<Integer, Match> entry : allMatches.entrySet()){
            Match mtc = entry.getValue();
            if(mtc.getStatus() == MatchStatus.FINISHED){
                pq.add(mtc);
            }
        }

        System.out.println("\n\n----------------------News---------------------\n");

   // two articles for the last match played
        if(!pq.isEmpty()){
            setTeams(pq.peek());
            scoreResultOutput(pq.peek());
            goalsResultOutput(pq.peek(), allPlayers);
            pq.poll();
        }
        if(!pq.isEmpty()){
            setTeams(pq.peek());
            scoreResultOutput(pq.peek());
        }
    }

    private void scoreResultOutput (Match match){

        StringBuilder builder = new StringBuilder();
        builder.append("‼️").append(match.getDate().getMonth()).append("/").append(match.getDate().getDayOfMonth())
                .append("/").append(match.getDate().getYear()).append(" ");
        // display news
        if(!isEqualResult()){
            int diff = winnerTeam.getScore() - defeatedTeam.getScore();
            if(diff > 2){
                builder.append(" Dramatic Loss\n").append(winnerTeam.getTeamName()).append(" defeated ")
                        .append(defeatedTeam.getTeamName()).append(" with huge difference of ")
                        .append(winnerTeam.getScore()).append("-").append(defeatedTeam.getScore()).append("...\n");
            }
            else{
                builder.append(defeatedTeam.getTeamName()).append(" Falls Short\n").append(winnerTeam.getTeamName())
                        .append(" defeated ").append(defeatedTeam.getTeamName()).append(" with small difference of ")
                        .append(winnerTeam.getScore()).append("-").append(defeatedTeam.getScore()).append("...\n");
            }
        }
        else{
            if(winnerTeam.getScore() == 0) {
                builder.append(" Dramatic Draw for both ").append(getWinnerTeam().getTeamName())
                        .append(" and ").append(getDefeatedTeam().getTeamName()).append("\n")
                        .append(winnerTeam.getTeamName()).append(" and ").append(defeatedTeam.getTeamName())
                        .append(" tied at ").append(winnerTeam.getScore()).append("-").append(winnerTeam.getScore())
                        .append(".\nThis resulted in both teams loosing the championship...\n");
            }
            else{
                builder.append(winnerTeam.getTeamName()).append(" and ").append(defeatedTeam.getTeamName())
                        .append(" end match with same score\n").append("Match concluded with ")
                        .append(winnerTeam.getTeamName()).append(" and ").append(defeatedTeam.getTeamName())
                        .append(" tied at ").append(winnerTeam.getScore()).append("-").append(winnerTeam.getScore())
                        .append(".\nThis resulted in both teams being declared winners...\n");
            }
        }
        System.out.println(builder);
    }

    void goalsResultOutput(Match match, HashMap<String, Player> allPlayers){
        StringBuilder builder = new StringBuilder();
        builder.append("‼️").append(match.getDate().getMonth()).append("/").append(match.getDate().getDayOfMonth())
                .append("/").append(match.getDate().getYear()).append(" ");
        if(match.getGoals().isEmpty()){
            builder.append(" Match Ends in Goalless Stalemate! \n The Euro 2024 match between ")
                    .append(winnerTeam.getTeamName()).append(" and ").append(defeatedTeam.getTeamName())
                    .append(" ended in a 0-0 draw. \n").append("Despite numerous chances from both sides, " +
                            "neither team managed to find the back of the net...\n");
        }
        if(match.getGoals().size() == 1){
            builder.append(" ").append(match.getRound().getStr()).append(" match encounters only ")
                    .append(match.getGoals().size()).append(" goal\n");
            if(match.getHomeTeam().getScore() > match.getAwayTeam().getScore()) {
                builder.append(allPlayers.get(match.getGoals().getFirst().getPlayerID()).getName())
                        .append("'s goal secured the win of ").append(match.getHomeTeam().getTeamName()).append("\n");
            }
            else{
                builder.append(allPlayers.get(match.getGoals().getFirst().getPlayerID()).getName())
                        .append("'s goal secured the win of ").append(match.getAwayTeam().getTeamName())
                        .append("...\n");
            }
        }
        else {
            builder.append(match.getRound().getStr()).append(" match encounters ").append(match.getGoals().size())
                    .append(" goals\n");
            builder.append("All players including ");
            for(int i = 0; i < match.getGoals().size(); i++){
                if(i != match.getGoals().size() - 1){
                    builder.append(allPlayers.get(match.getGoals().get(i).getPlayerID()).getName()).append(", ");
                }
                else{
                    builder.append(allPlayers.get(match.getGoals().get(i).getPlayerID()).getName())
                            .append("\ncontributed to the final score for both teams...\n");
                }
            }
        }
        System.out.println(builder);
    }
}

