package tracker.tournament;
import java.util.HashMap;
import java.util.Map;

public class GroupStanding {
    private int playedMatches;
    private int wins;
    private int draws;
    private int losses;
    private int points;

    public GroupStanding(){
        playedMatches = 0;
        wins = 0;
        draws = 0;
        losses = 0;
        points = 0;
    }

    public int getDraws() {return draws;}
    public int getLosses() {return losses;}
    public int getPlayedMatches() {return playedMatches;}
    public int getPoints() {return points;}
    public int getWins() {return wins;}
    public void incrementPlayedMatches() {playedMatches++;}
    public void incrementWins() {wins++;}
    public void incrementDraws() {draws++;}
    public void incrementLosses() {losses++;}
    public int calculatePoints() {
        return (wins*3 + draws);
    }

    public void calculateStanding(String team, HashMap<Integer, Match> allMatches) {
        for (Map.Entry<Integer, Match> matchEntry : allMatches.entrySet()) {
            Match match = matchEntry.getValue();

            if ((match.getHomeTeam().getTeamName().equals(team)
                    || match.getAwayTeam().getTeamName().equals(team)) &&
                    match.getStatus().equals(MatchStatus.FINISHED)) {
               incrementPlayedMatches();
                if (match.getHomeTeam().getTeamName().equals(team)) {
                    if (match.getHomeTeam().getScore() > match.getAwayTeam().getScore()) {
                        incrementWins();
                    } else if (match.getHomeTeam().getScore() == match.getAwayTeam().getScore()) {
                        incrementDraws();
                    } else {
                        incrementLosses();
                    }
                } else {
                    if (match.getAwayTeam().getScore() > match.getHomeTeam().getScore()) {
                        incrementWins();
                    } else if (match.getAwayTeam().getScore() == match.getHomeTeam().getScore()) {
                        incrementDraws();
                    } else {
                        incrementLosses();
                    }
                }
            }
        }
    }
}