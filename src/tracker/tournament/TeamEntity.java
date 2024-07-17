package tracker.tournament;

public class TeamEntity implements Comparable<TeamEntity>{
    private String teamName;
    private int score;

    public TeamEntity() {
        setTeamName("");
        setScore(0);
    }
    public TeamEntity(String teamName, int score){
        setTeamName(teamName);
        setScore(score);
    }
    public void setTeamName(String teamName) {this.teamName = teamName;}
    public void setScore(int score) {this.score = score;}
    public String getTeamName() {return teamName;}
    public int getScore() {return score;}

    public int compareTo(TeamEntity other) {
        return Integer.compare(other.score, this.score);
    }
}
