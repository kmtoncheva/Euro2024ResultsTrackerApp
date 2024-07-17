package tracker.tournament;
import java.util.List;

public class Team {
    private String name;
    private Group group;
    private List<String> teamMembers;
    private String coach;
    private int totalGoals = 0;
    private int totalCardsYellow = 0;
    private int totalCardsRed = 0;

    public Team(String name, Group group, List<String> teamMembers, String coach){
        setName(name);
        setGroup(group);
        setTeamMembers(teamMembers);
        setCoach(coach);
    }

    public void setGroup(Group group) { this.group = group; }
    public void setName(String name) { this.name = name; }
    public void setCoach(String coach) { this.coach = coach; }
    public void setTeamMembers(List<String> teamMembers) {
        if(teamMembers.size() <= 0 || teamMembers.size() > 23){
            throw new IllegalArgumentException("Invalid number of players for team");
        }
        this.teamMembers = teamMembers;
    }

    public String getName() { return name; }
    public Group getGroup() { return group; }
    public List<String> getTeamMembers() { return teamMembers; }
    public String getCoach() { return coach; }
    public int getTotalGoals() { return totalGoals; }
    public void incrementGoals() { totalGoals++; }
    public int getTotalCardsYellow() {return totalCardsYellow;}
    public int getTotalCardsRed() {return totalCardsRed;}
    public void incrementCardsYellow() { totalCardsYellow++; }
    public void incrementCardsRed() { totalCardsYellow++; }
}
