package tracker.tournament;

import java.security.PublicKey;

public enum TournamentRound {
    GROUP_STAGE("Group stage"),
    ROUND_OF_16("Round of 16"),
    QUARTERFINALS("Quarter-Finals"),
    SEMIFINALS("Semi-finals"),
    FINAL("Final");

    private final String str;
    private TournamentRound(String str){
        this.str = str;
    }
    public String getStr() {
        return str;
    }
}