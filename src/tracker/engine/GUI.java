package tracker.engine;
import tracker.commands.*;
import tracker.tournament.Match;
import tracker.tournament.Player;
import tracker.tournament.Team;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class GUI {
    private final JFrame jFrame;
    private static LocalDateTime finalDate;
    private CommandAPI cmd;

    public GUI(){
        jFrame = new JFrame("Euro 2024 Results Tracker App");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(600, 400);
        jFrame.setLayout(null);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("src/tracker/engine/euro.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        panel.setLayout(null);
        jFrame.setContentPane(panel);
        //jFrame.setVisible(true);
        System.out.println("\uD83C\uDFC6 Euro 2024 \uD83C\uDFC6");

        finalDate = LocalDateTime.of(2024, 7, 17,
                23, 59, 59);
    }

    public void config(HashMap<String, Player> allPlayers, HashMap<String, Team> allTeams,
                       HashMap<Integer, Match> allMatches){
        setCountdown();
        setButtons(allPlayers, allTeams, allMatches);
        jFrame.setVisible(true);
    }

    private void setCountdown(){
        JLabel label = new JLabel();
        label.setBounds(0,40,600,30);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.YELLOW);
        label.setLayout(null);
        jFrame.getContentPane().add(label, BorderLayout.CENTER);

        // start counter until the final
        Thread thread = new Thread(()->{
            while(true) {
                LocalDateTime now = LocalDateTime.now();
                Duration duration = Duration.between(now, finalDate);

                if (duration.isNegative() || duration.isZero()) {
                    label.setText("Euro 2024 is over!");
                    break;
                }
                long remHours = duration.toHours();
                if(remHours < 24){
                    long remMin = duration.toMinutes() % 60;
                    long remSec = duration.toSeconds() % 60;
                    label.setText("\rCountdown to kick-off : " + remHours + " hours " +  remMin +
                            " minutes " +  remSec +  " seconds!");
                }
                else{
                    long remDays = duration.toDays();
                    if(remDays == 1){
                        label.setText("\rCountdown to kick-off : " + remDays + " day only!");
                    }
                    else {
                        label.setText("\rCountdown to kick-off : " + remDays + " days!");
                    }
                }
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    System.out.println("Sleep interupted" + e.getMessage());
                }
            }
        });
        thread.start();
    }

    private void setButtons(HashMap<String, Player> allPlayers, HashMap<String, Team> allTeams,
                            HashMap<Integer, Match> allMatches){
        JButton matchesButton = new JButton("Matches");
        matchesButton.setBounds(40,100,240,40);
        matchesButton.setLayout(null);
        matchesButton.setFont(new Font("Arial", Font.BOLD, 15));
        jFrame.getContentPane().add(matchesButton);
        matchesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmd = new MatchesCommand(allMatches);
                cmd.execute();
            }
        });

        JButton listTeamsButton = new JButton("List all participating teams");
        listTeamsButton.setBounds(40,155,240,40);
        listTeamsButton.setLayout(null);
        listTeamsButton.setFont(new Font("Arial", Font.BOLD, 15));
        jFrame.getContentPane().add(listTeamsButton);
        listTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmd = new ListTeamsCommand(allPlayers, allTeams);
                cmd.execute();
            }
        });

        JButton listMatchesButton = new JButton("Complete schedule");
        listMatchesButton.setBounds(40,210,240,40);
        listMatchesButton.setLayout(null);
        listMatchesButton.setFont(new Font("Arial", Font.BOLD, 15));
        jFrame.getContentPane().add(listMatchesButton);
        listMatchesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmd = new ListMatchesCommand(allMatches);
                cmd.execute();
            }
        });

        JButton statsButton = new JButton("Display statistics");
        statsButton.setBounds(310,100,240,40);
        statsButton.setLayout(null);
        statsButton.setFont(new Font("Arial", Font.BOLD, 15));
        jFrame.getContentPane().add(statsButton);
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmd = new StatsCommand(allPlayers, allTeams);
                cmd.execute();
            }
        });

        JButton articlesButton = new JButton("News articles");
        articlesButton.setBounds(310,155,240,40);
        articlesButton.setLayout(null);
        articlesButton.setFont(new Font("Arial", Font.BOLD, 15));
        jFrame.getContentPane().add(articlesButton);
        articlesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmd = new ArticlesCommand(allPlayers, allMatches);
                cmd.execute();
            }
        });

        JButton groupStandingsButton = new JButton("Current group standings");
        groupStandingsButton.setBounds(310,210,240,40);
        groupStandingsButton.setLayout(null);
        groupStandingsButton.setFont(new Font("Arial", Font.BOLD, 15));
        jFrame.getContentPane().add(groupStandingsButton);
        groupStandingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmd = new GroupStandingsCommand(allTeams, allMatches);
                cmd.execute();
                // forgot to add a button for upcoming game prediction feature so added the future here
                CommandAPI cmd2 = new GamePredictionCommand(allMatches);
                cmd2.execute();
            }
        });
    }
}

