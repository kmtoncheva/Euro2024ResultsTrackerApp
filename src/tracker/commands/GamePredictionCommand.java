package tracker.commands;
import tracker.tournament.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
//import static tracker.commands.GroupStandingsCommand.calculateStanding;

public class GamePredictionCommand implements CommandAPI{
    @Override
    public void execute(HashMap<String, Player> allPlayers, HashMap<String, Team> allTeams,
                        HashMap<Integer, Match> allMatches) {

        System.out.println("\n\n-----------UPCOMING Game prediction-----------\n");

        for(Map.Entry<Integer, Match> entry : allMatches.entrySet()){
            MatchStatus curStatus = entry.getValue().getStatus();
            if(curStatus == MatchStatus.UPCOMING || curStatus == MatchStatus.LIVE){
                predictWinner(entry.getValue().getHomeTeam(), entry.getValue().getAwayTeam(), allMatches);
            }
        }
    }

    private void predictWinner(TeamEntity homeTeam, TeamEntity awayTeam, HashMap<Integer, Match> allMatches){
        GroupStanding groupStandingHome = new GroupStanding();
        groupStandingHome.calculateStanding(homeTeam.getTeamName(), allMatches);
        GroupStanding groupStandingAway = new GroupStanding();
        groupStandingAway.calculateStanding(awayTeam.getTeamName(), allMatches);
        //GroupStanding groupStandingHome = calculateStanding(homeTeam.getTeamName(), allMatches);
        //GroupStanding groupStandingAway = calculateStanding(awayTeam.getTeamName(), allMatches);

        float homeWinRate = (float) (groupStandingHome.getWins() + (0.5 * groupStandingHome.getDraws()))
                / groupStandingHome.getPlayedMatches();
        float awayWinRate = (float) (groupStandingAway.getWins() + (0.5 * groupStandingAway.getDraws()))
                / groupStandingAway.getPlayedMatches();
        if(homeWinRate == 0 && awayWinRate == 0){
            homeWinRate = awayWinRate = 1;
        }
        float homeScoreRate = homeWinRate / (homeWinRate + awayWinRate);
        float awayScoreRate = awayWinRate / (homeWinRate + awayWinRate);
        DecimalFormat df = new DecimalFormat("#.##%");

        System.out.println("\uD83D\uDC49 " + homeTeam.getTeamName() + " (" + df.format(homeScoreRate) + ") - " +
                awayTeam.getTeamName() + " (" + df.format(awayScoreRate) + ")");
    }
}
