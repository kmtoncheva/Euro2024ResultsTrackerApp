package tracker.tournament;

public final class Goal {
    private int minute;
    private String playerID;
    private String teamName;

    public Goal(int minute, String playerID, String teamName){
       setPlayerID(playerID);
       setTeamName(teamName);
       setMinute(minute);
    }

    public void setTeamName(String teamName) {this.teamName = teamName;}
    public void setMinute(int minute) {
        if(minute <= 0 || minute > 100){
            throw new IllegalArgumentException("Invalid argument : minute of goal");
        }
        this.minute = minute;
    }
    public void setPlayerID(String playerID) {this.playerID = playerID;}
    public String getPlayerID() {return playerID;}
    public String getTeamName() {return teamName;}
    public int getMinute() {return minute;}
}
