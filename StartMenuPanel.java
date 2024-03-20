import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuPanel extends JPanel {

    //letrehozunk egy peldany a Menubol - "NAGY MENU"
    private MainFrame mainFrame;

    private JPanel panel;
    private JLabel title;
    private JButton startButton;
    private JButton exitButton;
    private JButton volumeButton;


    public StartMenuPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        //initVariables();
        //addComponents();
        title = new JLabel("SNAKE GAME");
        title.setHorizontalAlignment(JLabel.CENTER);

        startButton = new JButton("START");
        exitButton = new JButton("EXIT");

        ImageIcon icon = new ImageIcon("volume.png");
        Image img = icon.getImage();
        Image smallImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon smallIcon = new ImageIcon(smallImg);
        volumeButton = new JButton("Hang", smallIcon);

        //gombok merete
        int width = 500, height = 300;
        Dimension buttonSize = new Dimension(width, height);
        startButton.setSize(buttonSize);
        exitButton.setSize(buttonSize);

        setLayout(new BorderLayout());
        //kep hozzaadasa
        ImageIcon snakeImageIcon = new ImageIcon("header.png");
        JLabel snakeImageLabel = new JLabel(snakeImageIcon);

        //add(title, BorderLayout.NORTH);
        //seged panelek
        JPanel felso = new JPanel();
        JPanel also = new JPanel();
        JPanel jobb = new JPanel();
        JPanel bal = new JPanel();

        felso.setPreferredSize(new Dimension(600, 300));
        also.setPreferredSize(new Dimension(600, 150));
        bal.setPreferredSize(new Dimension(200, 600));
        jobb.setPreferredSize(new Dimension(200, 600));

        felso.add(snakeImageLabel, BorderLayout.CENTER);
        add(felso, BorderLayout.NORTH);
        add(bal, BorderLayout.WEST);
        add(jobb, BorderLayout.EAST);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JPanel segedPanel1 = new JPanel();
        JPanel segedPanel2 = new JPanel();

        segedPanel1.setLayout(new FlowLayout());
        segedPanel1.add(startButton);
        segedPanel1.add(exitButton);

        segedPanel2.setLayout(new BorderLayout());
        segedPanel2.add(volumeButton, BorderLayout.SOUTH);
        //middlePanel.add(volumeButton, BorderLayout.SOUTH);

        middlePanel.add(segedPanel1, BorderLayout.NORTH);
        middlePanel.add(segedPanel2, BorderLayout.SOUTH);
        add(middlePanel, BorderLayout.CENTER);

        exitButton.addActionListener(e -> System.exit(0));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //elinditjuk a jatekot
                mainFrame.SnakeCard(); // ez lesz a jatek
            }
        });

        volumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ImageIcon newIcon;

                //meg kell nezni az isMuted valtozot
                if (mainFrame.isMuted()) {
                    mainFrame.UnMuteMusic();
                    newIcon = new ImageIcon("volume.png");
                } else {
                    mainFrame.MuteMusic();
                    newIcon = new ImageIcon("mute.png");
                }
                //-> annak fuggvenyeben meghivjuk az egyik fgv-t

                //->atcsereljuk a kepet
                Image newImg = newIcon.getImage();
                Image newSmallImg = newImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon newSmallIcon = new ImageIcon(newSmallImg);
                volumeButton.setIcon(newSmallIcon);
            }
        });
    }

    void initVariables() {
        title = new JLabel("SNAKE GAME");
        title.setHorizontalAlignment(JLabel.CENTER);

        startButton = new JButton("START");
        exitButton = new JButton("EXIT");

        ImageIcon icon = new ImageIcon("volume.png");
        Image img = icon.getImage();
        Image smallImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon smallIcon = new ImageIcon(smallImg);
        volumeButton = new JButton("Hang", smallIcon);
    }

    void addComponents() { //+beallitasok, elrendezesek stb, minden mas
        //gombok merete
        int width = 500, height = 300;
        Dimension buttonSize = new Dimension(width, height);
        startButton.setSize(buttonSize);
        exitButton.setSize(buttonSize);

        setLayout(new BorderLayout());
        //kep hozzaadasa
        ImageIcon snakeImageIcon = new ImageIcon("header.png");
        JLabel snakeImageLabel = new JLabel(snakeImageIcon);

        //add(title, BorderLayout.NORTH);
        //seged panelek
        JPanel felso = new JPanel();
        JPanel also = new JPanel();
        JPanel jobb = new JPanel();
        JPanel bal = new JPanel();

        felso.setPreferredSize(new Dimension(600, 300));
        also.setPreferredSize(new Dimension(600, 150));
        bal.setPreferredSize(new Dimension(200, 600));
        jobb.setPreferredSize(new Dimension(200, 600));

        felso.add(snakeImageLabel, BorderLayout.CENTER);
        add(felso, BorderLayout.NORTH);
        add(bal, BorderLayout.WEST);
        add(jobb, BorderLayout.EAST);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JPanel segedPanel1 = new JPanel();
        JPanel segedPanel2 = new JPanel();

        segedPanel1.setLayout(new FlowLayout());
        segedPanel1.add(startButton);
        segedPanel1.add(exitButton);

        segedPanel2.setLayout(new BorderLayout());
        segedPanel2.add(volumeButton, BorderLayout.SOUTH);
        //middlePanel.add(volumeButton, BorderLayout.SOUTH);

        middlePanel.add(segedPanel1, BorderLayout.NORTH);
        middlePanel.add(segedPanel2, BorderLayout.SOUTH);
        add(middlePanel, BorderLayout.CENTER);
    }

}
