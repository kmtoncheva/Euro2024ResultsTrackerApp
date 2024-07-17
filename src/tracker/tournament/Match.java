package tracker.tournament;
import java.time.LocalDateTime;
import java.util.List;

public class Match implements Comparable<Match>{
    private final int ID;
    private LocalDateTime date;
    private TeamEntity homeTeam;
    private TeamEntity awayTeam;
    private TournamentRound round;
    private MatchStatus status;
    private List<Goal> goals;
    private List<Card> cards;

    public Match(int ID, LocalDateTime date, TeamEntity homeTeam, TeamEntity awayTeam,
                 TournamentRound round, MatchStatus status, List<Goal> goals, List<Card> cards){

        this.ID = ID;
        setDate(date);
        setHomeTeam(homeTeam);
        setAwayTeam(awayTeam);
        setRound(round);
        setStatus(status);
        setCards(cards);
        setGoals(goals);
    }

    public void setRound(TournamentRound round) {this.round = round;}
    public void setDate(LocalDateTime date) {this.date = date;}
    public void setAwayTeam(TeamEntity awayTeam) {this.awayTeam = awayTeam;}
    public void setGoals(List<Goal> goals) {this.goals = goals;}
    public void setHomeTeam(TeamEntity homeTeam) {this.homeTeam = homeTeam;}
    public void setStatus(MatchStatus status) {this.status = status;}
    public void setCards(List<Card> cards) {this.cards = cards;}

    public int getID() {return ID;}
    public TournamentRound getRound() {return round;}
    public TeamEntity getAwayTeam() {return awayTeam;}
    public TeamEntity getHomeTeam() {return homeTeam;}
    public List<Goal> getGoals() {return goals;}
    public LocalDateTime getDate() {return date;}
    public MatchStatus getStatus() {return status;}
    public List<Card> getCards() {return cards;}

    public int compareTo(Match other) {
        return other.getDate().compareTo(this.getDate());
    }
}
