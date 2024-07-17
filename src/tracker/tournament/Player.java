package tracker.tournament;

public class Player {
    private final String ID;
    private String name;
    private Type position;
    private int totalGoals = 0;

    public Player(String ID , String name, Type position){
        this.ID = ID;
        setName(name);
        setPosition(position);
    }

    void setName(String name){
        this.name = name;
    }
    public void setPosition(Type position) {
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public String getID() {
        return ID;
    }
    public Type getPosition() {
        return position;
    }
    public int getTotalGoals() { return totalGoals; }
    public void incrementGoals() { totalGoals++; }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
