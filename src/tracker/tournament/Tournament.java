package tracker.tournament;
import java.util.HashMap;

public final class Tournament {
    private static Tournament instance = null;
    private  HashMap<String, Player> allPlayers;
    private  HashMap<String, Team> allTeams;
    private  HashMap<Integer, Match> allMatches;
    private int totalCardsYellow = 0;
    private int totalCardsRed = 0;
    private int totalGoals = 0;

    private Tournament(){
        allPlayers = new HashMap<>();
        allTeams = new HashMap<>();
        allMatches = new HashMap<>();
    }
    public static Tournament getInstance(){
        if(instance == null){
            instance = new Tournament();
        }
        return instance;
    }

    public  void setAllMatches(HashMap<Integer, Match> allMatches) {this.allMatches = allMatches;}
    public  void setAllPlayers(HashMap<String, Player> allPlayers) {this.allPlayers = allPlayers;}
    public  void setAllTeams(HashMap<String, Team> allTeams) {this.allTeams = allTeams;}
    public HashMap<String, Player> getAllPlayers() {
        return allPlayers;
    }
    public HashMap<String, Team> getAllTeams() {
        return allTeams;
    }
    public HashMap<Integer, Match> getAllMatches() { return allMatches; }

    public  int getTotalGoals() {return totalGoals;}
    public void incrementGoals() { totalGoals++; }
    public  int getTotalCardsYellow() {return totalCardsYellow;}
    public  int getTotalCardsRed() {return totalCardsRed;}
    public void incrementCardsYellow() { totalCardsYellow++; }
    public void incrementCardsRed() { totalCardsYellow++; }
}
