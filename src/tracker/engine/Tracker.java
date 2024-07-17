package tracker.engine;
import tracker.files.FileHandlerMatches;
import tracker.files.FileHandlerPlayers;
import tracker.files.FileHandlerTeams;
import tracker.tournament.*;
import java.util.List;
import java.util.Map;

public class Tracker {
    Tournament tournament;
    public Tracker() {
        tournament = Tournament.getInstance();
    }

    public void run() {
        FileHandlerPlayers fileHandlerPlayers = new FileHandlerPlayers();
        FileHandlerTeams fileHandlerTeams = new FileHandlerTeams();
        FileHandlerMatches fileHandlerMatches = new FileHandlerMatches();

        // upload info from JSON files
        tournament.setAllPlayers(fileHandlerPlayers.uploadFromFile());
        tournament.setAllTeams(fileHandlerTeams.uploadFromFile());
        tournament.setAllMatches(fileHandlerMatches.uploadFromFile());
        uploadGoalsAndCards();

        GUI gui = new GUI();
        gui.config(tournament.getAllPlayers(), tournament.getAllTeams(),
                tournament.getAllMatches());
    }

    private void uploadGoalsAndCards(){
        for(Map.Entry<Integer, Match> entry : tournament.getAllMatches().entrySet()){
            if(entry.getValue().getStatus() == MatchStatus.FINISHED ||
                    entry.getValue().getStatus() == MatchStatus.LIVE) {
                List<Goal> allGoals = entry.getValue().getGoals();
                if (!allGoals.isEmpty()) {
                    for (Goal goal : allGoals) {
                        String plID = goal.getPlayerID();
                        String teamID = goal.getTeamName();
                        tournament.getAllPlayers().get(plID).incrementGoals();
                        tournament.getAllTeams().get(teamID).incrementGoals();
                        tournament.incrementGoals();
                    }
                }
                List<Card> allCards = entry.getValue().getCards();
                if (!allCards.isEmpty()) {
                    for (Card card : allCards) {
                        String teamID = card.getTeamName();
                        if(card.getType() == CardType.RED){
                            tournament.getAllTeams().get(teamID).incrementCardsRed();
                            tournament.incrementCardsRed();
                        }
                        else {
                            tournament.getAllTeams().get(teamID).incrementCardsYellow();
                            tournament.incrementCardsYellow();
                        }
                    }
                }
            }
        }
    }
}
