import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PauseMenuPanel extends JPanel implements ActionListener, KeyListener {

    //letrehozunk egy peldanyt a Menubol - "NAGY MENU"
    private MainFrame mainFramePeldany;
    private JPanel panel;
    private JButton resumeButton;
    private JButton restartButton;
    private JButton quitButton;
    private JButton muteButton;

    public PauseMenuPanel(MainFrame mainFramePeldany) {

        this.mainFramePeldany = mainFramePeldany;

        //initVariables();
        //addComponents();
        panel = new JPanel(new BorderLayout());
        //buttonok
        resumeButton = new JButton("RESUME");
        restartButton = new JButton("RESTART");
        quitButton = new JButton("QUIT");

        ImageIcon icon;
        if (mainFramePeldany.isMuted()) {
            icon = new ImageIcon("volume.png");
        } else {
            icon = new ImageIcon("mute.png");
        }
        Image img = icon.getImage();
        Image smallImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon smallIcon = new ImageIcon(smallImg);
        muteButton = new JButton("Hang", smallIcon);

        //seged panelek
        JPanel felso = new JPanel();
        JPanel also = new JPanel();
        JPanel jobb = new JPanel();
        JPanel bal = new JPanel();

        felso.setPreferredSize(new Dimension(600, 300));
        also.setPreferredSize(new Dimension(600, 150));
        bal.setPreferredSize(new Dimension(200, 600));
        jobb.setPreferredSize(new Dimension(200, 600));


        add(felso, BorderLayout.NORTH);
        add(bal, BorderLayout.WEST);
        add(jobb, BorderLayout.EAST);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JPanel segedPanel1 = new JPanel();
        JPanel segedPanel2 = new JPanel();

        //gombok merete
        int width = 200, height = 100;
        Dimension buttonSize = new Dimension(width, height);
        resumeButton.setPreferredSize(buttonSize);
        restartButton.setPreferredSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);

        segedPanel1.setLayout(new FlowLayout());
        segedPanel1.add(resumeButton);
        segedPanel1.add(restartButton);
        segedPanel1.add(quitButton);


        segedPanel2.setLayout(new BorderLayout());
        segedPanel2.add(muteButton, BorderLayout.SOUTH);

        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        muteButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        middlePanel.add(segedPanel1, BorderLayout.NORTH);
        middlePanel.add(segedPanel2, BorderLayout.SOUTH);
        panel.add(middlePanel, BorderLayout.CENTER);

        quitButton.addActionListener(e -> System.exit(0)); //ablak bezarasa

        resumeButton.addActionListener(new ActionListener() { //folytatjuk a jatekot
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFramePeldany.getSnakeGame().resumeGame();
            }
        });

        restartButton.addActionListener(new ActionListener() { //atlepunk a fomenure
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFramePeldany.getSnakeGame().resetGame();
                //mainFramePeldany.getSnakeGame().resumeGame();
                mainFramePeldany.StartCard();
            }
        });

        muteButton.addActionListener(new ActionListener() { //lenemitas vagy hangadas
            @Override
            public void actionPerformed(ActionEvent e) {

                ImageIcon newIcon;

                //meg kell nezni az isMuted valtozot
                if (mainFramePeldany.isMuted()) {
                    mainFramePeldany.UnMuteMusic();
                    newIcon = new ImageIcon("volume.png");
                } else {
                    mainFramePeldany.MuteMusic();
                    newIcon = new ImageIcon("mute.png");
                }
                //-> annak fuggvenyeben meghivjuk az egyik fgv-t

                //->atcsereljuk a kepet
                Image newImg = newIcon.getImage();
                Image newSmallImg = newImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon newSmallIcon = new ImageIcon(newSmallImg);
                muteButton.setIcon(newSmallIcon);
            }
        });
    }

    private void initVariables() {

        panel = new JPanel(new BorderLayout());
        //buttonok
        resumeButton = new JButton("RESUME");
        restartButton = new JButton("RESTART");
        quitButton = new JButton("QUIT");

        ImageIcon icon;
        if (mainFramePeldany.isMuted()) {
            icon = new ImageIcon("volume.png");
        } else {
            icon = new ImageIcon("mute.png");
        }
        Image img = icon.getImage();
        Image smallImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon smallIcon = new ImageIcon(smallImg);
        muteButton = new JButton("Hang", smallIcon);
    }

    public void addComponents() {
        //seged panelek
        JPanel felso = new JPanel();
        JPanel also = new JPanel();
        JPanel jobb = new JPanel();
        JPanel bal = new JPanel();

        felso.setPreferredSize(new Dimension(600, 300));
        also.setPreferredSize(new Dimension(600, 150));
        bal.setPreferredSize(new Dimension(200, 600));
        jobb.setPreferredSize(new Dimension(200, 600));


        add(felso, BorderLayout.NORTH);
        add(bal, BorderLayout.WEST);
        add(jobb, BorderLayout.EAST);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JPanel segedPanel1 = new JPanel();
        JPanel segedPanel2 = new JPanel();

        //gombok merete
        int width = 200, height = 100;
        Dimension buttonSize = new Dimension(width, height);
        resumeButton.setPreferredSize(buttonSize);
        restartButton.setPreferredSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);

        segedPanel1.setLayout(new FlowLayout());
        segedPanel1.add(resumeButton);
        segedPanel1.add(restartButton);
        segedPanel1.add(quitButton);


        segedPanel2.setLayout(new BorderLayout());
        segedPanel2.add(muteButton, BorderLayout.SOUTH);

        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        muteButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        middlePanel.add(segedPanel1, BorderLayout.NORTH);
        middlePanel.add(segedPanel2, BorderLayout.SOUTH);
        panel.add(middlePanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { //ez kell nekunk
        if (e.getKeyCode() == KeyEvent.VK_H) {
            mainFramePeldany.getSnakeGame().resumeGame();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public JPanel getPanel() {
        return panel;
    }
}
