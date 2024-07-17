package tracker.tournament;

public class Card {
    private int minute;
    private CardType type;
    private String playerID;
    private String teamName;

    public Card(int minute, CardType type, String playerID, String teamName){
        setMinute(minute);
        setType(type);
        setPlayerID(playerID);
        setTeamName(teamName);
    }

    public void setTeamName(String teamName) {this.teamName = teamName;}
    public void setMinute(int minute) {this.minute = minute;}
    public void setType(CardType type) {this.type = type;}
    public void setPlayerID(String playerID) {this.playerID = playerID;}
    public String getTeamName() {return teamName;}
    public String getPlayerID() {return playerID;}
    public CardType getType() {return type;}
    public int getMinute() {return minute;}
}
