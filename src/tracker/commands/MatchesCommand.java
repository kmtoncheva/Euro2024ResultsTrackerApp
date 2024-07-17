package tracker.commands;
import tracker.tournament.Match;
import tracker.tournament.MatchStatus;
import tracker.tournament.Player;
import tracker.tournament.Team;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MatchesCommand implements CommandAPI{
    private final Map<Integer, Match> allMatches;

    public MatchesCommand(Map<Integer, Match> allMatches){
        this.allMatches = allMatches;
    }

    @Override
    public void execute() {
        LocalDate currentDate = LocalDate.now();
        boolean upcomingMatches = false;

        System.out.println("\n----------------------Today---------------------\n");
        for(Map.Entry<Integer, Match> entry : allMatches.entrySet()){
            Match mch = entry.getValue();
            LocalDate now = mch.getDate().toLocalDate();
            if(now.isEqual(currentDate) && mch.getStatus() != MatchStatus.FINISHED) {
                String builder = "\uD83D\uDD52" + mch.getDate().getHour() + ":" +
                        mch.getDate().getMinute() + " " + mch.getHomeTeam().getTeamName()
                        + " - " + mch.getAwayTeam().getTeamName();

                System.out.println(builder);
                upcomingMatches = true;
            }
        }
        if(!upcomingMatches){
            System.out.println("\uD83D\uDE14There is no upcoming matches for today!");
        }

        System.out.println("\n---------------------Finished--------------------\n");
        for(Map.Entry<Integer, Match> entry : allMatches.entrySet()){
            Match mch = entry.getValue();
            if(mch.getStatus() == MatchStatus.FINISHED) {
                String builder = "\uD83D\uDC49" + mch.getHomeTeam().getScore() + ":" +
                        mch.getAwayTeam().getScore() + " " + mch.getHomeTeam().getTeamName() +
                        " - " + mch.getAwayTeam().getTeamName();
                System.out.println(builder);
            }
        }
    }
}