import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout layout;
    private JPanel menuPanel;

    //GLOBALIS VALTOZOK
    private boolean isMuted;// = false; //zene
    private boolean isStarted;// = false; //jatek
    boolean isPaused;// = false; //ha behivjuk a menut akkor szunetel

    private Sound sound = new Sound();

    private StartMenuPanel menu1; // = new StartMenuPanel(this);
    private SnakeGame snakeGame;
    private PauseMenuPanel menu2;


    public MainFrame() {
        layout = new CardLayout();
        menuPanel = new JPanel(); //fontos

        menuPanel.setLayout(layout);
        add(menuPanel);

        sound.SetSound(0); //hatterzene
        sound.Play();
        //GLOBALIS VALTOZOK
        isMuted = false; //zene
        isStarted = false; //jatek
        isPaused = false; //ha behivjuk a menut akkor szunetel
        //jatek - szint - kilepes - hang

        //elso - Start menu
        menu1 = new StartMenuPanel(this);
        menuPanel.add(menu1, "P1");

        //masodik - JATEK
        int boardWidth = 600;
        int boardHeight = boardWidth;
        snakeGame = new SnakeGame(this, boardWidth, boardHeight);

        menuPanel.add(snakeGame, "P2");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 665);
        setVisible(true);

        //harmadik - Pause menu - kicsiMenu
        menu2 = new PauseMenuPanel(this);
        menuPanel.add(menu2.getPanel(), "P3");
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    public StartMenuPanel getMenu1() {
        return menu1;
    }

    public SnakeGame getSnakeGame() {
        return snakeGame;
    }

    public PauseMenuPanel getMenu2() {
        return menu2;
    }

    public void MuteMusic() {
        this.sound.Stop();
        this.isMuted = true;
    }

    public void UnMuteMusic() {
        this.sound.Play();
        this.sound.Loop();
        this.isMuted = false;
    }

    public void StartCard() {
        layout.show(menuPanel, "P1");
        menu1.requestFocus();
    }

    public void SnakeCard() {
        //layout.next(menuPanel);
        layout.show(menuPanel, "P2");
        snakeGame.requestFocus();
    }

    public void PauseCard() {
        //layout.next(menuPanel);
        layout.show(menuPanel, "P3");
        menu2.requestFocus();
    }

    public void NextPanelCard() {
        layout.next(menuPanel);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

}
