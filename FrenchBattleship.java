// Jason He & Jeet Singh
// 4/21/2025
// Week #2
// FrenchBattleship.java  
/* This is our game project, French Battleship. The goal is to familiarize
 * the user with French 2 words and phrases to prepare them for Semester 1
 * Finals, while making it a fun and interactive experience by integrating
 * it into a battleship game with modifications. First, the user randomly
 * selects between ten battleship layouts (there is a randomize button).
 * The user then attempts to answers a French 2 level question. 
 */

// Import everything
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.BorderFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

// This is the class that sets the JFrames size, it's location and then 
// it setsResizable to false. - Made by Jason
public class FrenchBattleship
{
    public FrenchBattleship()
    {
    }
    

    public static void main(String[] args)
    {
       FrenchBattleship fb = new FrenchBattleship();
       fb.run();
    }
    public void run() 
    {
         JFrame frame = new JFrame("French Battleship");
         frame.setSize(1000, 800);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLocation(300, 0);
         frame.setResizable(false);
         FrenchBattleshipHolder fbh = new FrenchBattleshipHolder();
         frame.getContentPane().add(fbh);
         frame.setVisible(true);
    }


}

/* FrenchBattleshipHolder holds the game together. It sets the background to 
 * Cyan and uses a card layout. It passes the cards into the respective 
 * panel and then it adds those panels to the card layout. - made by Jason
 */
class FrenchBattleshipHolder extends JPanel 
{
    private CardLayout cards; // instance of cardlayout

    public FrenchBattleshipHolder() 
    {
        setBackground(Color.CYAN);
        cards = new CardLayout();
        setLayout(cards);

        Information info = new Information();
        FirstPagePanel fpp = new FirstPagePanel(this, cards, info);
        CreditsPanel cp = new CreditsPanel(this, cards);
        HelpPanel hp = new HelpPanel(this, cards);
        InstructionsPanel ip = new InstructionsPanel(this, cards);
        HighScoresPanel hsp = new HighScoresPanel(this, cards);
        BattleshipLayoutPanel blp = new BattleshipLayoutPanel(this, cards, info);
        FrenchQuestionPanel fqp = new FrenchQuestionPanel(this, cards, info);
        AttackPanel ap = new AttackPanel(this, cards, info);
        ShopPanel sp = new ShopPanel(this, cards, info);
        YouLosePanel ylp = new YouLosePanel(this, cards, info);

        add(fpp, "First");
        add(cp, "Credits");
        add(hp, "Help");
        add(ip, "Instructions");
        add(hsp, "HighScores");
        add(blp, "BattleshipLayout");
        add(fqp, "FrenchQuestion");
        add(ap, "Attack");
        add(sp, "Shop");
        add(ylp, "YouLose");
    }
}

/* Makes a panel and adds the background image to it, then adds all 
 * componenets. Loads the image by calling a method in the
 * Information class. It creates buttons by calling the create menu
 * button method. Then it adds all of the components to the main 
 * panel- Jeet
 */
class FirstPagePanel extends JPanel 
{
    private FrenchBattleshipHolder panelCards;
    private CardLayout cards;
    private Information info;
    private Image bgImage; // background image on first page

    // Define colors at class level
    private Color titleColor; // color of the title
    private Color buttonTextColor; // color of the text on button
    private Font titleFont; // title's font
    private Font buttonFont; // button text font

    /* Sets null layout and adds neccesary components */
    public FirstPagePanel(FrenchBattleshipHolder panelCardsIn, CardLayout cardsIn, Information infoIn) 
    {
        panelCards = panelCardsIn;
        cards = cardsIn;
        info = infoIn;
        setLayout(null);

        // Initialize colors and fonts
        titleColor = Color.BLACK;
        buttonTextColor = Color.BLACK;
        titleFont = new Font("Arial", Font.BOLD, 36);
        buttonFont = new Font("Arial", Font.BOLD, 24);

        // Load background image
        bgImage = info.loadImage("BgImage.jpg");

        // Add game title
        JLabel titleLabel = new JLabel("French Battleship");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(titleColor);
        titleLabel.setBounds(350, 50, 400, 50);
        add(titleLabel);

        // Create buttons for the menu
        JButton playButton = createMenuButton("Play", 150);
        JButton rulesButton = createMenuButton("Rules", 220);
        JButton highscoresButton = createMenuButton("Highscores", 290);
        JButton helpButton = createMenuButton("Help", 360);
        JButton creditsButton = createMenuButton("Credits", 430);
        JButton exitButton = createMenuButton("Exit", 500);

        // Add action listeners to buttons
        rulesButton.addActionListener(new RulesButtonHandler());
        helpButton.addActionListener(new HelpButtonHandler());
        creditsButton.addActionListener(new CreditsButtonHandler());
        exitButton.addActionListener(new ExitButtonHandler());
        highscoresButton.addActionListener(new HighButtonHandler());
        playButton.addActionListener(new PlayButtonHandler());
    }

    /* This is a helper method to create a menu button. We pass in a
     * string and the y position. Then we use the setBounds method to 
     * set the location and size of the button. Then we add the button
     * and finally return the button. - Jason
    */
    public JButton createMenuButton(String text, int yPosition) 
    {
        JButton button = new JButton(text);
        button.setBounds(400, yPosition, 200, 50);
        button.setFont(buttonFont);
        add(button);
        return button;
    }

    // This method is used to paint the background image to the panel
    // by using the drawImage method. - Jeet
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }

    /* This is an action listener for the rules button. It uses the
     * cards.show method to switch to the instructions panel. - Jeet
     */
    class RulesButtonHandler implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "Instructions");
        }
    }

    /* This is an action listener for the help button. It uses the
     * cards.show method to switch to the help panel. - Jeet
     */
    class HelpButtonHandler implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "Help");
        }
    }

    /* This is an action listener for the credits button. It uses the
     * cards.show method to switch to the credits panel. - Jeet
     */
    class CreditsButtonHandler implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt)
        {
            cards.show(panelCards, "Credits");
        }
    }
    
    /* This is an action listener for the exit button. It uses the
     * System.exit method to exit the program. - Jeet
     */
    class ExitButtonHandler implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            System.exit(1);
        }
    }
    
    /* This is an action listener for the highscores button. It uses the
     * cards.show method to switch to the highscores panel. - Jeet
     */
    class HighButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "HighScores");
        }
    }
    
    /* This is an action listener for the play button. It uses the
     * cards.show method to switch to the battleship layout panel. - Jeet
     */
    class PlayButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "BattleshipLayout");
        }
    }
}

/*   This is the information class. It is used to store the name of the
* user and the welcome label. It also has a method to load an image so 
* that it can be used for any image. (An example of polymorphism) - Jason
* It also has a method to setup the computer's ships.
*/
class Information 
{
    private String name; // name of user
    private JLabel welcomeLabel; // JLabel to welcome user, get anytime
    private Timer timer; // the running timer needed for all play panels
    private int userLayout; // stores the user's selected layout
    private int computerLayout; // stores the computer's random layout
    private boolean[][] computerShips; // 2D array to track computer's ship positions (true = ship, false = empty)
    private boolean[][] playerHits; // 2D array to track player's hits (true = hit, false = miss or not attempted)

    /* initialize */
    public Information() 
    {
        name = "";
        welcomeLabel = new JLabel("Welcome");
        timer = new Timer(1000, null);
        userLayout = 0;
        computerLayout = 0;
        computerShips = new boolean[15][15]; // Initialize with all false (no ships)
        playerHits = new boolean[15][15]; // Initialize with all false (no hits)
    }

    // simple getter method
    public String getName() 
    {
        return name;
    }

    // simple setter method
    public void setName(String nameIn) 
    {
        name = nameIn;
    }
    
    // simple getter method
    public JLabel getWelcomeLabel() 
    {
        return welcomeLabel;
    }

    // simple setter method
    public void setWelcomeLabel(String nameIn) 
    {
        welcomeLabel.setText("Welcome " + nameIn);
    }

    // simple getter method
    public Timer getTimer()
    {
        return timer;
    }

    // simple setter method
    public void setTimer(Timer timerIn)
    {
        timer = timerIn;
    }
    
    // simple getter method for user layout
    public int getUserLayout()
    {
        return userLayout;
    }
    
    // simple setter method for user layout
    public void setUserLayout(int layoutIn)
    {
        userLayout = layoutIn;
    }
    
    // simple getter method for computer layout
    public int getComputerLayout()
    {
        return computerLayout;
    }
    
    // simple setter method for computer layout
    public void setComputerLayout(int layoutIn)
    {
        computerLayout = layoutIn;
        // When computer layout is set, also setup the ship positions
        setupComputerShips(layoutIn);
    }

    /* helps load an image whenever called. very convienent. */
    public Image loadImage(String imageIn)
    {
        Image bothPicture = null;
        try 
        {
            bothPicture = ImageIO.read(new File(imageIn));
        } 
        catch (IOException e) 
        {
            System.err.println("\n\n" + bothPicture + "can't be found.\n\n");
            e.printStackTrace();
        }
        return bothPicture;
    }

    // Check if a position contains a ship
    public boolean isShipAt(int row, int col)
    {
        if (row >= 0 && row < 15 && col >= 0 && col < 15) 
        {
            return computerShips[row][col];
        }
        return false;
    }
    
    // Record a hit at a position
    public void recordHit(int row, int col)
    {
        if (row >= 0 && row < 15 && col >= 0 && col < 15) 
        {
            playerHits[row][col] = true;
        }
    }
    
    // Check if a position has been hit
    public boolean isHit(int row, int col)
    {
        if (row >= 0 && row < 15 && col >= 0 && col < 15) 
        {
            return playerHits[row][col];
        }
        return false;
    }
    
    // Setup computer ships based on layout number
    public void setupComputerShips(int layoutNumber)
    {
        // Reset the ship positions
        for (int i = 0; i < 15; i++) 
        {
            for (int j = 0; j < 15; j++) 
            {
                computerShips[i][j] = false;
            }
        }
        
        // Set up the specific layout
        if (layoutNumber == 1) 
        {
            setupLayout1();
        } 
        else if (layoutNumber == 2) 
        {
            setupLayout2();
        } 
        else if (layoutNumber == 3) 
        {
            setupLayout3();
        } 
        else if (layoutNumber == 4) 
        {
            setupLayout4();
        } 
        else if (layoutNumber == 5) 
        {
            setupLayout5();
        } 
        else if (layoutNumber == 6) 
        {
            setupLayout6();
        } 
        else if (layoutNumber == 7) 
        {
            setupLayout7();
        } 
        else if (layoutNumber == 8) 
        {
            setupLayout8();
        } 
        else if (layoutNumber == 9) 
        {
            setupLayout9();
        } 
        else if (layoutNumber == 10) 
        {
            setupLayout10();
        } 
        else if (layoutNumber == 11) 
        {
            setupLayout11();
        } 
        else if (layoutNumber == 12) 
        {
            setupLayout12();
        } 
        else if (layoutNumber == 13) 
        {
            setupLayout13();
        } 
        else if (layoutNumber == 14) 
        {
            setupLayout14();
        } 
        else if (layoutNumber == 15) 
        {
            setupLayout15();
        } 
        else 
        {
			setupLayout1();
   
        }
        
        }
    
    // Helper method to place a horizontal ship
    public void placeHorizontalShip(int row, int startCol, int length)
    {
        for (int i = 0; i < length; i++) 
        {
            if (startCol + i < 15) 
            {
                computerShips[row][startCol + i] = true;
            }
        }
    }
    
    // Helper method to place a vertical ship
    public void placeVerticalShip(int startRow, int col, int length)
    {
        for (int i = 0; i < length; i++) 
        {
            if (startRow + i < 15) 
            {
                computerShips[startRow + i][col] = true;
            }
        }
    }
    
    // Setup layout 1 ship positions to match the provided image
    public void setupLayout1()
    {
        // Horizontal ships
        placeHorizontalShip(0, 3, 5);
        placeHorizontalShip(1, 10, 5);
        placeHorizontalShip(3, 4, 5);
        placeHorizontalShip(5, 10, 5);
        placeHorizontalShip(10, 3, 5);
        placeHorizontalShip(14, 2, 5);
        
        // Vertical ships
        placeVerticalShip(1, 0, 5);
        placeVerticalShip(8, 1, 5);
        placeVerticalShip(3, 3, 5);
        placeVerticalShip(4, 7, 5);
        placeVerticalShip(8, 10, 5);
        placeVerticalShip(10, 14, 5);
    }
    
    // Setup layout 2 ship positions
    public void setupLayout2()
    {
        // Horizontal ships
        placeHorizontalShip(0, 5, 5);
        placeHorizontalShip(3, 10, 5);
        placeHorizontalShip(4, 1, 5);
        placeHorizontalShip(12, 0, 5);
        placeHorizontalShip(12, 10, 5);
        placeHorizontalShip(14, 5, 5);
        
        // Vertical ships
        placeVerticalShip(6, 0, 5);
        placeVerticalShip(6, 2, 5);
        placeVerticalShip(6, 6, 5);
        placeVerticalShip(1, 8, 10);
        placeVerticalShip(6, 12, 5);
    }
    
    // Setup layout 3 ship positions
    public void setupLayout3()
    {
        // Horizontal ships
        placeHorizontalShip(0, 10, 5);
        placeHorizontalShip(3, 3, 5);
        placeHorizontalShip(7, 6, 5);
        placeHorizontalShip(9, 4, 5);
        placeHorizontalShip(10, 11, 5);
        placeHorizontalShip(13, 7, 5);
        
        // Vertical ships
        placeVerticalShip(1, 0, 5);
        placeVerticalShip(7, 1, 5);
        placeVerticalShip(0, 2, 5);
        placeVerticalShip(10, 4, 5);
        placeVerticalShip(1, 9, 5);
        placeVerticalShip(3, 13, 5);
    }
    
    // Setup layout 4 ship positions
    public void setupLayout4()
    {
        // Placeholder for layout 4
        // Horizontal ships
        placeHorizontalShip(1, 2, 5);
        placeHorizontalShip(4, 7, 5);
        placeHorizontalShip(7, 10, 5);
        placeHorizontalShip(9, 6, 5);
        placeHorizontalShip(11, 4, 5);
        placeHorizontalShip(13, 6, 5);
       
        // Vertical ships
        placeVerticalShip(10, 0, 5);
        placeVerticalShip(3, 1, 5);
        placeVerticalShip(9, 2, 5);
        placeVerticalShip(4, 4, 5);
        placeVerticalShip(9, 12, 5);
        placeVerticalShip(1, 13, 5);
    }
    
    // Setup layout 5 ship positions
    public void setupLayout5()
    {
        // Placeholder for layout 5
        // Horizontal ships
        placeHorizontalShip(2, 4, 5);
        placeHorizontalShip(5, 10, 5);
        placeHorizontalShip(8, 2, 5);
        placeHorizontalShip(11, 6, 5);
        placeHorizontalShip(14, 4, 5);
        
        // Vertical ships
        placeVerticalShip(1, 9, 5);
        placeVerticalShip(3, 13, 5);
        placeVerticalShip(9, 5, 5);
        placeVerticalShip(10, 10, 5);
        placeVerticalShip(12, 3, 5);
    }
    
    // Setup layout 6 ship positions
    public void setupLayout6()
    {
        // Placeholder for layout 6
        // Horizontal ships
        placeHorizontalShip(3, 2, 5);
        placeHorizontalShip(6, 8, 5);
        placeHorizontalShip(9, 1, 5);
        placeHorizontalShip(12, 7, 5);
        placeHorizontalShip(14, 13, 5);
        
        // Vertical ships
        placeVerticalShip(0, 7, 5);
        placeVerticalShip(2, 11, 5);
        placeVerticalShip(7, 4, 5);
        placeVerticalShip(8, 13, 5);
        placeVerticalShip(10, 1, 5);
    }
    
    // Setup layout 7 ship positions
    public void setupLayout7()
    {
        // Placeholder for layout 7
        // Horizontal ships
        placeHorizontalShip(1, 5, 5);
        placeHorizontalShip(4, 9, 5);
        placeHorizontalShip(7, 3, 5);
        placeHorizontalShip(10, 11, 5);
        placeHorizontalShip(13, 6, 5);
        
        // Vertical ships
        placeVerticalShip(2, 2, 5);
        placeVerticalShip(3, 7, 5);
        placeVerticalShip(8, 10, 5);
        placeVerticalShip(9, 5, 5);
        placeVerticalShip(11, 8, 5);
    }
    
    // Setup layout 8 ship positions
    public void setupLayout8()
    {
        // Placeholder for layout 8
        // Horizontal ships
        placeHorizontalShip(0, 3, 5);
        placeHorizontalShip(3, 7, 5);
        placeHorizontalShip(6, 1, 5);
        placeHorizontalShip(9, 10, 5);
        placeHorizontalShip(12, 5, 5);
        
        // Vertical ships
        placeVerticalShip(1, 1, 5);
        placeVerticalShip(2, 5, 5);
        placeVerticalShip(7, 8, 5);
        placeVerticalShip(8, 12, 5);
        placeVerticalShip(10, 3, 5);
    }
    
    // Setup layout 9 ship positions
    public void setupLayout9()
    {
        // Placeholder for layout 9
        // Horizontal ships
        placeHorizontalShip(2, 6, 5);
        placeHorizontalShip(5, 2, 5);
        placeHorizontalShip(8, 9, 5);
        placeHorizontalShip(11, 4, 5);
        placeHorizontalShip(14, 7, 5);
        
        // Vertical ships
        placeVerticalShip(0, 4, 5);
        placeVerticalShip(3, 8, 5);
        placeVerticalShip(6, 12, 5);
        placeVerticalShip(9, 2, 5);
        placeVerticalShip(12, 10, 5);
    }
    
    // Setup layout 10 ship positions
    public void setupLayout10()
    {
        // Placeholder for layout 10
        // Horizontal ships
        placeHorizontalShip(1, 8, 5);
        placeHorizontalShip(4, 3, 5);
        placeHorizontalShip(7, 11, 5);
        placeHorizontalShip(10, 2, 5);
        placeHorizontalShip(13, 9, 5);
        
        // Vertical ships
        placeVerticalShip(2, 5, 5);
        placeVerticalShip(5, 9, 5);
        placeVerticalShip(8, 3, 5);
        placeVerticalShip(9, 7, 5);
        placeVerticalShip(11, 13, 5);
    }
    
    // Setup layout 11 ship positions
    public void setupLayout11()
    {
        // Placeholder for layout 11
        // Horizontal ships
        placeHorizontalShip(0, 9, 5);
        placeHorizontalShip(3, 4, 5);
        placeHorizontalShip(6, 8, 5);
        placeHorizontalShip(9, 3, 5);
        placeHorizontalShip(12, 11, 5);
        
        // Vertical ships
        placeVerticalShip(1, 3, 5);
        placeVerticalShip(4, 7, 5);
        placeVerticalShip(7, 1, 5);
        placeVerticalShip(10, 6, 5);
        placeVerticalShip(11, 10, 5);
    }
    
    // Setup layout 12 ship positions
    public void setupLayout12()
    {
        // Placeholder for layout 12
        // Horizontal ships
        placeHorizontalShip(2, 7, 5);
        placeHorizontalShip(5, 3, 5);
        placeHorizontalShip(8, 10, 5);
        placeHorizontalShip(11, 5, 5);
        placeHorizontalShip(14, 2, 5);
        
        // Vertical ships
        placeVerticalShip(0, 5, 5);
        placeVerticalShip(3, 10, 5);
        placeVerticalShip(6, 2, 5);
        placeVerticalShip(9, 8, 5);
        placeVerticalShip(12, 13, 5);
    }
    
    // Setup layout 13 ship positions
    public void setupLayout13()
    {
        // Placeholder for layout 13
        // Horizontal ships
        placeHorizontalShip(1, 4, 5);
        placeHorizontalShip(4, 10, 5);
        placeHorizontalShip(7, 2, 5);
        placeHorizontalShip(10, 8, 5);
        placeHorizontalShip(13, 5, 5);
        
        // Vertical ships
        placeVerticalShip(2, 3, 5);
        placeVerticalShip(5, 8, 5);
        placeVerticalShip(8, 5, 5);
        placeVerticalShip(9, 11, 5);
        placeVerticalShip(11, 1, 5);
    }
    
    // Setup layout 14 ship positions
    public void setupLayout14()
    {
        // Placeholder for layout 14
        // Horizontal ships
        placeHorizontalShip(0, 2, 5);
        placeHorizontalShip(3, 9, 5);
        placeHorizontalShip(6, 4, 5);
        placeHorizontalShip(9, 1, 5);
        placeHorizontalShip(12, 7, 5);
       
        // Vertical ships
        placeVerticalShip(1, 6, 5);
        placeVerticalShip(4, 1, 5);
        placeVerticalShip(7, 9, 5);
        placeVerticalShip(10, 4, 5);
        placeVerticalShip(11, 11, 5);
    }
    
    // Setup layout 15 ship positions
    public void setupLayout15()
    {
        // Placeholder for layout 15
        // Horizontal ships
        placeHorizontalShip(2, 8, 5);
        placeHorizontalShip(5, 4, 5);
        placeHorizontalShip(8, 11, 5);
        placeHorizontalShip(11, 2, 5);
        placeHorizontalShip(14, 8, 5);
        
        // Vertical ships
        placeVerticalShip(0, 3, 5);
        placeVerticalShip(3, 6, 5);
        placeVerticalShip(6, 9, 5);
        placeVerticalShip(9, 12, 5);
        placeVerticalShip(12, 4, 5);
    }
    
}

/* 
* This is the credits panel. It displays the credits for the game.
* It also has a home button that takes the user back to the main menu. - Jeet
*/
class CreditsPanel extends JPanel 
{
    private FrenchBattleshipHolder panelCards;
    private CardLayout cards;

    /* sets border layout and all the neccessary components for credits */
    public CreditsPanel(FrenchBattleshipHolder ph, CardLayout cl) 
    {
        panelCards = ph;
        cards = cl;
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.CYAN);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.CYAN);
        JLabel titleLabel = new JLabel("Credits");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titlePanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 1, 10, 30));
        contentPanel.setBackground(Color.CYAN);

        JLabel codeLabel = createCreditLabel("Code: Jason He and Jeet Singh");
        JLabel graphicsLabel = createCreditLabel("Graphics: Jason He and Jeet Singh");
        JLabel testingLabel = createCreditLabel("Testing and Debugging: Jason He and Jeet Singh");
        JLabel thanksLabel = createCreditLabel("Special Thanks: Mr. Conlin, Justin Chen");

        contentPanel.add(codeLabel);
        contentPanel.add(graphicsLabel);
        contentPanel.add(testingLabel);
        contentPanel.add(thanksLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.CYAN);
        JButton homeButton = new JButton("Back to Menu");
        homeButton.addActionListener(new HomeButtonListener());
        buttonPanel.add(homeButton);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /* example of polymorphism: method can be called anytime to create label */
    public JLabel createCreditLabel(String text) 
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    // listens for home button
    class HomeButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "First");
        }
    }
}

/* 
* This is the instructions panel. It displays the instructions for the game.
* It also has a return button that takes the user back to the main menu. - Jeet
*/
class InstructionsPanel extends JPanel 
{
    private FrenchBattleshipHolder panelCards;
    private CardLayout cards;

    public InstructionsPanel(FrenchBattleshipHolder ph, CardLayout cl) 
    {
        panelCards = ph;
        cards = cl;
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.CYAN);
        JLabel titleLabel = new JLabel("Instructions");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titlePanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPanel.setBackground(Color.CYAN);

        // html for the instructions (bold coins)
        String instructionsText = "<html><div style='text-align: center; width: 700px;'>" +
            "Ahoy matey, welcome wanderer! You have been challenged by French pirates to a duel of French in a<br>" +
            "classic game of battleship! You will select between ten battleship layouts (there is a randomize button).<br>" +
            "You have the option of Attack or to Get Resources. If you choose to get resources, you will have to<br>" +
            "answer a question simply to gather resources and give up your attack turn to do so! However, if you get<br>" +
            "it correct, you get <b>200 coins</b>. If you choose to Attack you will need to answer a randomly generated<br>" +
            "French question. If you get it right (type the right answer, not case sensitive), you get to choose a point<br>" +
            "on the grid to attack, if they hit something they get 50 additional coins. Wrong and your turn will be<br>" +
            "skipped! However, make sure to thoroughly review why you got the question incorrect so you don't<br>" +
            "make the same mistake in the future. This process is repeated until the you sink the all of the<br>" +
            "computer's battleships or if the computer opponent sinks all of your ships. Every time, the computer<br>" +
            "will have a different, randomized battleship layout so you cannot repeat what they have sunk last time!" +
            "</div></html>";

        JLabel instructionsTextLabel = new JLabel(instructionsText);
        instructionsTextLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(instructionsTextLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.CYAN);
        JButton returnButton = new JButton("Return To Home");
        returnButton.addActionListener(new ReturnButtonListener());
        buttonPanel.add(returnButton);

        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // button handler to return to sweet old home
    class ReturnButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "First");
        }
    }
}

/* 
* This is the help panel. It displays the help information for the game.
* It also has a home button that takes the user back to the main menu. - Jason
*/
class HelpPanel extends JPanel 
{
    private FrenchBattleshipHolder panelCards;
    private CardLayout cards;

    public HelpPanel(FrenchBattleshipHolder ph, CardLayout cl) 
    {
        panelCards = ph;
        cards = cl;
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.CYAN);
        JLabel titleLabel = new JLabel("Help");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titlePanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPanel.setBackground(Color.CYAN);

        // html for help text
        String helpText = "<html><div style='text-align: center; width: 700px;'>" +
            "Are some of the questions too hard for you? Do you feel like you are unable<br>" +
            "to solve a single problem even in easy mode? Well fear not, this is the help<br>" +
            "section where we will help you overcome your challenges.<br><br>" +
            "Firstly, try to figure out what the question is asking. A very prestigious study<br>" +
            "says that 99.99% of the time people answer questions wrong, they don't<br>" +
            "even know what the question means. So a very promising first step would<br>" +
            "probably to figure out what the question means.<br><br>" +
            "Can't figure out what the question means? Well maybe you can build on your<br>" +
            "previous knowledge. While you may not know what the question means you<br>" +
            "can probably figure out what a big part of the question means and guess the<br>" +
            "part that you don't know.<br><br>" +
            "If you still can't figure the question out remember that it is ok to ask for<br>" +
            "help or to search up the answer, just make sure not to make a habit of it." +
            "</div></html>";

        JLabel helpTextLabel = new JLabel(helpText);
        helpTextLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(helpTextLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.CYAN);
        JButton homeButton = new JButton("Back to Menu");
        homeButton.addActionListener(new HomeButtonListener());
        buttonPanel.add(homeButton);

        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // button to return home 
    class HomeButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "First");
        }
    }
}

/* 
* This is the highscores panel. It displays the highscores for the game.
* It also has a return button that takes the user back to the main menu. - Jason
*/
class HighScoresPanel extends JPanel
{
    private FrenchBattleshipHolder panelCards;
    private CardLayout cards;

    public HighScoresPanel(FrenchBattleshipHolder ph, CardLayout cl) 
    {
        panelCards = ph;
        cards = cl;
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.CYAN);
        JLabel titleLabel = new JLabel("High Scores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titlePanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPanel.setBackground(Color.CYAN);

        String instructionsText = "<html><div style='text-align: center; width: 700px;'>" +
            "Jordan Carter - <b>3 minutes and 14 seconds</b><br>" +
            "George W Bush - <b>10 minutes and 15 seconds</b><br>" +
            "Christiano Ronaldo - <b>11 minutes and 28 seconds</b><br>" +
            "Jarrad Higgins - <b>12 minutes and 8 seconds<br></div></html>";

        JLabel instructionsTextLabel = new JLabel(instructionsText);
        instructionsTextLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(instructionsTextLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.CYAN);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.addActionListener(new ExitButtonListener());

        JButton homeButton = new JButton("Go to home");
        homeButton.setFont(new Font("Arial", Font.BOLD, 20));
        homeButton.addActionListener(new HomeButtonListener());

        buttonPanel.add(exitButton);
        buttonPanel.add(homeButton);

        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // button to return home
    class ExitButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            System.exit(1);
            /// maybe in the future: a rectangle pops up, saying, "are you
            /// sure you want to exit?" so the user doesnt lose progress.
        }
    }

    // listens for home button
    class HomeButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "First");
        }
    }
}

/* 
* This is the battleship layout panel. It allows the user to select a battleship layout.
* Inside this panel we can see an image of the grid and a reroll button. It also uses
* mouse listeners to handle the reroll button. It also has a continue button that
* takes the user to the French question panel. - Jeet
*/
class BattleshipLayoutPanel extends JPanel 
{
    private FrenchBattleshipHolder panelCards;
    private Information info;
    private CardLayout cards;
    
    private Image gridImage; // image of the grid
    private Image rerollImage; // green recycle image
    
    private GridPanel gridPanel; 
    private RerollPanel rerollPanel;
    private TimerPanel timerPanel;
    
    private int currentLayout; // current, blank grid = 0, random 1-15
    private boolean isFirstView; // is it a blank grid? (t/f)
    private Timer timer; // the timer
    private int seconds; // running seconds
    private JLabel timerLabel; // label of timer to display
    private JButton continueButton; // Continue button reference
    private JLabel gridLabel; // Added for the new ContinueButtonListener

    public BattleshipLayoutPanel(FrenchBattleshipHolder ph, CardLayout cl, Information infoIn) 
    {
        info = infoIn;
        panelCards = ph;
        cards = cl;
        isFirstView = true;
        seconds = 0;
        
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());

        // Initialize timer components
        timerPanel = new TimerPanel();
        timerLabel = new JLabel("Time: 0s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setForeground(Color.WHITE);
        timerPanel.add(timerLabel);

        // Create timer that updates every second
        timer = info.getTimer();
        timer.addActionListener(new TimerListener());

        try 
        {
            // Load the initial grid image
            gridImage = ImageIO.read(new File("Grid.png"));
            rerollImage = ImageIO.read(new File("Reroll.png"));
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading images: " + e);
        }

        // Create the title at the top with timer
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.CYAN);

        JPanel titleLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleLabelPanel.setBackground(Color.CYAN);
        JLabel titleLabel = new JLabel("Choose a Battleship Layout");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabelPanel.add(titleLabel);

        titlePanel.add(titleLabelPanel, BorderLayout.CENTER);
        titlePanel.add(timerPanel, BorderLayout.EAST);

        // Create grid panel that paints the grid image
        gridPanel = new GridPanel();

        // Create the right side panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.CYAN);

        // Create "Swap arrangement" label
        JPanel swapLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        swapLabelPanel.setBackground(Color.CYAN);
        JLabel swapLabel = new JLabel("Swap arrangement");
        swapLabel.setFont(new Font("Arial", Font.BOLD, 24));
        swapLabelPanel.add(swapLabel);

        // Create reroll panel that paints the reroll image
        rerollPanel = new RerollPanel();
        rerollPanel.addMouseListener(new RerollMouseListener());

        JPanel rerollContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rerollContainer.setBackground(Color.CYAN);
        rerollContainer.add(rerollPanel);

        // Add components to right panel
        rightPanel.add(swapLabelPanel, BorderLayout.NORTH);
        rightPanel.add(rerollContainer, BorderLayout.CENTER);

        // Create bottom panel with Continue button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.CYAN);
        continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 20));
        continueButton.addActionListener(new ContinueButtonListener());
        bottomPanel.add(continueButton);

        // Add all panels to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize current layout
        currentLayout = 0;

        // Added for the new ContinueButtonListener
        gridLabel = new JLabel();
        bottomPanel.add(gridLabel);
    }

    // Method to change the layout randomly - Jeet
    public void changeLayout()
    {
        if (isFirstView)
        {
            isFirstView = false;
            currentLayout = (int)(Math.random() * 15) + 1;
        }
        else 
        {
            // Generate a random number between 1 and 15
            int newLayout;
            do 
            {
                newLayout = (int)(Math.random() * 15) + 1;
            } while (newLayout == currentLayout);

            currentLayout = newLayout;
        }

        // Use the existing info instance instead of creating a new one
        gridImage = info.loadImage("layout" + currentLayout + ".png");
        gridPanel.repaint();
    }

    // Grid panel class, draws the actual image
    class GridPanel extends JPanel
    {
        public GridPanel()
        {
            setBackground(Color.CYAN);
            setPreferredSize(new Dimension(500, 500));
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(gridImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Reroll panel class, draws the actual image
    class RerollPanel extends JPanel
    {
        public RerollPanel()
        {
            setBackground(Color.CYAN);
            setPreferredSize(new Dimension(100, 100));
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(rerollImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // listener for mouse. Four are not needed.
    class RerollMouseListener implements MouseListener
    {
        public void mouseClicked(MouseEvent evt)
        {
            changeLayout();
        }

        public void mousePressed(MouseEvent evt) 
        {
        }

        public void mouseReleased(MouseEvent evt) 
        {
        }

        public void mouseEntered(MouseEvent evt) 
        {
        }

        public void mouseExited(MouseEvent evt) 
        {
        }
    }

    // Continues onto the question panel
    class ContinueButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            // Save the user's selected layout
            info.setUserLayout(currentLayout);
            
            // Only continue if a layout has been selected
            if (currentLayout == 0 || gridImage.toString().contains("Grid.png")) 
            {
                // Display a message that layout needs to be selected
                // Instead of using JOptionPane, we'll set a message on a label
                gridLabel.setText("Please select a layout before continuing.");
                gridLabel.setForeground(Color.RED);
                return;
            }
            
            // Generate a random layout for the computer (1-15)
            int compLayout;
            do 
            {
                compLayout = (int)(Math.random() * 15) + 1;
            } while (compLayout == currentLayout); // Make sure it's different from user's
            
            info.setComputerLayout(compLayout);
            
            timer.start();
            cards.show(panelCards, "FrenchQuestion");
        }
    }

    /* This panel displays a black box with the timer text
     * It uses paintComponent to draw the background - Jason
     */
    class TimerPanel extends JPanel
    {
        public TimerPanel()
        {
            setBackground(Color.CYAN);
            setPreferredSize(new Dimension(150, 40));
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /* This listener updates the timer text every second
     * It increments the seconds counter and updates the label - Jason
     */
    class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            seconds++;
            timerLabel.setText("Time: " + seconds + "s");
        }
    }
}

/* 
* FrenchQuestionPanel displays French questions and evaluates user answers
* - Jeet
*/
class FrenchQuestionPanel extends JPanel 
{
    private FrenchBattleshipHolder panelCards;
    private Information info;
    private CardLayout cards;
    private TimerPanel timerPanel;
    
    private JTextField answerField; // answer field for user
    private String correctAnswer; // String of the correct answer
    private JLabel feedbackLabel; // the feedback to give back to user
    private Timer timer; // timer
    private int seconds; // running seconds
    private JLabel timerLabel; // label of running timer

    public FrenchQuestionPanel(FrenchBattleshipHolder ph, CardLayout cl, Information infoIn) 
    {
        info = infoIn;
        panelCards = ph;
        cards = cl;
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());

        correctAnswer = "comment";
        seconds = 0;

        // Initialize timer components
        timerPanel = new TimerPanel();
        timerLabel = new JLabel("Time: 0s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setForeground(Color.WHITE);
        timerPanel.add(timerLabel);

        // Create timer that updates every second
        timer = info.getTimer();
        timer.addActionListener(new TimerListener());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.CYAN);

        /// lots of junk to fix & organize later.
        JPanel questionPanel = new JPanel();
        questionPanel.setBackground(Color.CYAN);
        JLabel questionLabel = new JLabel("Fill in the blank: Savez vous __________");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 36));
        questionPanel.add(questionLabel);

        topPanel.add(questionPanel, BorderLayout.CENTER);
        topPanel.add(timerPanel, BorderLayout.EAST);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 20));
        centerPanel.setBackground(Color.CYAN);

        JPanel answerPanel = new JPanel();
        answerPanel.setBackground(Color.CYAN);
        JLabel answerLabel = new JLabel("Your Answer: Bonjour");
        answerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        answerPanel.add(answerLabel);

        centerPanel.add(answerPanel);

        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setBackground(Color.CYAN);
        JLabel incorrectLabel = new JLabel("Your Answer was ");
        incorrectLabel.setFont(new Font("Arial", Font.BOLD, 36));
        feedbackPanel.add(incorrectLabel);

        JLabel incorrectHighlightLabel = new JLabel("incorrect");
        incorrectHighlightLabel.setFont(new Font("Arial", Font.BOLD, 36));
        incorrectHighlightLabel.setForeground(Color.RED);
        feedbackPanel.add(incorrectHighlightLabel);

        JLabel answerWasLabel = new JLabel(" the answer was actually *");
        answerWasLabel.setFont(new Font("Arial", Font.BOLD, 36));
        feedbackPanel.add(answerWasLabel);

        JLabel correctAnswerLabel = new JLabel("comment");
        correctAnswerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        correctAnswerLabel.setForeground(Color.GREEN);
        feedbackPanel.add(correctAnswerLabel);

        JLabel closingStarLabel = new JLabel("*");
        closingStarLabel.setFont(new Font("Arial", Font.BOLD, 36));
        feedbackPanel.add(closingStarLabel);

        centerPanel.add(feedbackPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.CYAN);

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 20));
        continueButton.addActionListener(new ContinueButtonListener());
        buttonPanel.add(continueButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /* Continues onto the AttackPanel */ 
    class ContinueButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "Attack");
        }
    }

    /* This panel displays a black box with the timer text
     * It uses paintComponent to draw the background - Jason
     */
    class TimerPanel extends JPanel
    {
        public TimerPanel()
        {
            setBackground(Color.CYAN);
            setPreferredSize(new Dimension(150, 40));
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /* This listener updates the timer text every second
     * It increments the seconds counter and updates the label - Jason
     */
    class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            seconds++;
            timerLabel.setText("Time: " + seconds + "s");
        }
    }
}

/* 
* This is the Attack Panel, where the user is able to choose a point to 
* attack, as well as access thre shop. If the user buys something from the
* shop, it will show "[shop item] activated" on the attack page.  - Jason
* and Jeet
*/
class AttackPanel extends JPanel 
{
    private FrenchBattleshipHolder panelCards;
    private Information info;
    private CardLayout cards;
    private TimerPanel timerPanel;
    
    private Timer timer; // the timer
    private int seconds; // running seconds
    private JLabel timerLabel; // label of running timer
    private JButton continueButton; // continues to the next page
    private boolean hasClicked; // only continue if a grid segment is clicked
    private JButton[][] gridButtons; // 2d array for the grid + buttons
    private JLabel statusLabel; // displays hit/miss status

    public AttackPanel(FrenchBattleshipHolder ph, CardLayout cl, Information infoIn) 
    {
        panelCards = ph;
        cards = cl;
        info = infoIn;
        hasClicked = false;

        setBackground(Color.CYAN);
        setLayout(new BorderLayout());
        seconds = 0;

        // Initialize timer components
        timerPanel = new TimerPanel();
        timerLabel = new JLabel("Time: 0s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setForeground(Color.WHITE);
        timerPanel.add(timerLabel);

        // Create timer that updates every second
        timer = info.getTimer();
        timer.addActionListener(new TimerListener());

        // Create the title panel with timer
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.CYAN);

        // Add title label
        JLabel titleLabel = new JLabel("Choose a coordinate to attack");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add timer and title to top panel
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(timerPanel, BorderLayout.EAST);

        // Create grid panel with 15x15 buttons
        JPanel gridPanel = new JPanel(new GridLayout(15, 15, 2, 2));
        gridPanel.setBackground(Color.CYAN);
        gridButtons = new JButton[15][15];

        for(int i = 0; i < 15; i++) 
        {
            for(int j = 0; j < 15; j++) 
            {
                gridButtons[i][j] = new JButton();
                gridButtons[i][j].setPreferredSize(new Dimension(40, 25)); // Rectangular shape
                gridButtons[i][j].setBackground(Color.WHITE);
                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridButtons[i][j].addActionListener(new GridButtonListener(i, j));
                gridPanel.add(gridButtons[i][j]);
            }
        }

        // Create center panel to hold the grid and status label
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.CYAN);
        
        // Add status label
        statusLabel = new JLabel("Select a grid position to attack");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Add grid panel and status label to center panel
        centerPanel.add(gridPanel, BorderLayout.CENTER);
        centerPanel.add(statusLabel, BorderLayout.SOUTH);

        // Create bottom panel with Continue and Shop buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonPanel.setBackground(Color.CYAN);

        continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 20));
        continueButton.addActionListener(new ContinueButtonListener());
        continueButton.setEnabled(false);
        buttonPanel.add(continueButton);

        JButton shopButton = new JButton("Shop");
        shopButton.setFont(new Font("Arial", Font.BOLD, 20));
        shopButton.addActionListener(new ShopButtonListener());
        buttonPanel.add(shopButton);

        // Add all panels to the main layout
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /* Listens when a button in the grid is clicked */
    class GridButtonListener implements ActionListener 
    {
        private int row, col; // rows and columns of our 15x15 grid

        public GridButtonListener(int r, int c) 
        {
            row = r;
            col = c;
        }
        
        // Handles grid button clicks
        public void actionPerformed(ActionEvent evt) 
        {
            // Check if this position has already been hit
            if (info.isHit(row, col)) 
            {
                // Already hit this position - update message
                statusLabel.setText("You've already attacked this position!");
                return;
            }
            
            // Record this hit
            info.recordHit(row, col);
            
            // Check if there's a ship at this position
            if (info.isShipAt(row, col)) 
            {
                // Hit a ship - mark red and change button appearance
                JButton button = (JButton)evt.getSource();
                button.setBackground(Color.RED);
                button.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                button.setForeground(Color.WHITE);
                button.setText("HIT");
                statusLabel.setText("HIT! You found a ship at position (" + (row+1) + "," + (col+1) + ")!");
                statusLabel.setForeground(Color.RED);
            } 
            else 
            {
                // Missed - mark blue and change button appearance
                JButton button = (JButton)evt.getSource();
                button.setBackground(Color.BLUE);
                button.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                button.setForeground(Color.WHITE);
                button.setText("MISS");
                statusLabel.setText("MISS! No ship at position (" + (row+1) + "," + (col+1) + ")");
                statusLabel.setForeground(Color.BLUE);
            }
            
            hasClicked = true;
            continueButton.setEnabled(true);
        }
    }

    // continues onto the next panel
    class ContinueButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt) 
        {
            if(hasClicked) 
            {
                /// update show panel once we finish more
                cards.show(panelCards, "YouLose");
            }
        }
    }

    // goes to the shop
    class ShopButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "Shop");
        }
    }

    // panel where the timer is placed in
    class TimerPanel extends JPanel
    {
        public TimerPanel()
        {
            setBackground(Color.CYAN);
            setPreferredSize(new Dimension(150, 40));
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    // listens for our timer
    class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            seconds++;
            timerLabel.setText("Time: " + seconds + "s");
        }
    }
}

/* 
* ShopPanel can be accessed from the Attack Panel. The user can buy items 
* of their choice to help with the attacking of the battleships. - Jeet
* and Jason
*/
class ShopPanel extends JPanel 
{
    private FrenchBattleshipHolder panelCards;
    private Information info;
    private CardLayout cards;
    
    private Timer timer; // timer
    private int seconds; // running seconds 
    private JLabel timerLabel; // label to display running timer
    private Image backgroundImage; // background iamge in the shop (null)
    private Image radarImage; // image of radar for 3x3 radar
    private Image planeImage; // image of plane for airstrike
    private Image tripleImage; // image of 3 for triple attack
    private Image torpedoImage; // image of torpedo for torpedo attack
    private Image coinImage; // image of coin
    private JCheckBox radarCheckBox; // checkbox to buy
    private JCheckBox airstrikeCheckBox; // checkbox to buy
    private JCheckBox tripleAttackCheckBox; // checkbox to buy
    private JCheckBox torpedoCheckBox; // checkbox to buy
    private JButton buyButton; // buy & return to attack
    private JButton continueButton; // continue button to next page
    private int coins; // running number of coins, put into info later

    public ShopPanel(FrenchBattleshipHolder ph, CardLayout cl, Information infoIn) 
    {
        info = infoIn;
        panelCards = ph;
        cards = cl;
        coins = 500; // Starting coins

        setLayout(null); // Using absolute positioning to match the image exactly

        seconds = 0;

        // Load all images
        backgroundImage = info.loadImage("ShopBg.png");
        radarImage = info.loadImage("radar.png");
        planeImage = info.loadImage("plane.jpg");
        tripleImage = info.loadImage("three.png");
        torpedoImage = info.loadImage("torpedo.png");
        coinImage = info.loadImage("coin.jpg");

        // Create the timer display in top right
        timerLabel = new JLabel("Time: " + seconds + "s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setBackground(Color.BLACK);
        timerLabel.setOpaque(true);
        timerLabel.setBounds(852, 30, 150, 40);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        add(timerLabel);

        // Create timer that updates every second
        timer = info.getTimer();
        timer.addActionListener(new TimerListener());

        // Create title label (without white background)
        JLabel titleLabel = new JLabel("The Battle-Shop");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(320, 80, 400, 60);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);

        // Create right coin display (without white background)
        JLabel rightCoinsLabel = createCoinLabel(500);
        rightCoinsLabel.setForeground(Color.WHITE);
        rightCoinsLabel.setBounds(740, 80, 160, 60);
        add(rightCoinsLabel);

        // Scale images to smaller size
        int imageWidth = 160;
        int imageHeight = 160;

        // Create item labels with white text
        JLabel radarLabel = new JLabel("3x3 radar");
        radarLabel.setFont(new Font("Arial", Font.BOLD, 28));
        radarLabel.setForeground(Color.WHITE);
        radarLabel.setBounds(60, 170, 200, 40);
        add(radarLabel);

        JLabel airstrikeLabel = new JLabel("Airstrike");
        airstrikeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        airstrikeLabel.setForeground(Color.WHITE);
        airstrikeLabel.setBounds(300, 170, 200, 40);
        add(airstrikeLabel);

        JLabel tripleAttackLabel = new JLabel("Triple Attack");
        tripleAttackLabel.setFont(new Font("Arial", Font.BOLD, 28));
        tripleAttackLabel.setForeground(Color.WHITE);
        tripleAttackLabel.setBounds(520, 170, 250, 40);
        add(tripleAttackLabel);

        JLabel torpedoLabel = new JLabel("Torpedo");
        torpedoLabel.setFont(new Font("Arial", Font.BOLD, 28));
        torpedoLabel.setForeground(Color.WHITE);
        torpedoLabel.setBounds(760, 170, 200, 40);
        add(torpedoLabel);

        // Create image labels for each item
        JLabel radarImageLabel = new JLabel();
        
        radarImageLabel.setBounds(40, 220, imageWidth, imageHeight);
        add(radarImageLabel);

        JLabel planeImageLabel = new JLabel();
        
        planeImageLabel.setBounds(280, 220, imageWidth, imageHeight);
        add(planeImageLabel);

        JLabel tripleImageLabel = new JLabel();
        
        tripleImageLabel.setBounds(520, 220, imageWidth, imageHeight);
        add(tripleImageLabel);

        JLabel torpedoImageLabel = new JLabel();

        torpedoImageLabel.setBounds(760, 220, imageWidth, imageHeight);
        add(torpedoImageLabel);

        // Add cost labels directly (without panels)
        JLabel radarCostLabel = createCoinLabel(400);
        radarCostLabel.setForeground(Color.WHITE);
        radarCostLabel.setBounds(40, 400, 160, 40);
        add(radarCostLabel);

        JLabel airstrikeCostLabel = createCoinLabel(800);
        airstrikeCostLabel.setForeground(Color.WHITE);
        airstrikeCostLabel.setBounds(280, 400, 160, 40);
        add(airstrikeCostLabel);

        JLabel tripleCostLabel = createCoinLabel(400);
        tripleCostLabel.setForeground(Color.WHITE);
        tripleCostLabel.setBounds(520, 400, 160, 40);
        add(tripleCostLabel);

        JLabel torpedoCostLabel = createCoinLabel(2000);
        torpedoCostLabel.setForeground(Color.WHITE);
        torpedoCostLabel.setBounds(760, 400, 160, 40);
        add(torpedoCostLabel);

        // Add checkboxes
        radarCheckBox = new JCheckBox();
        radarCheckBox.setBounds(100, 450, 30, 30);
        radarCheckBox.setOpaque(false);
        add(radarCheckBox);

        airstrikeCheckBox = new JCheckBox();
        airstrikeCheckBox.setBounds(340, 450, 30, 30);
        airstrikeCheckBox.setOpaque(false);
        add(airstrikeCheckBox);

        tripleAttackCheckBox = new JCheckBox();
        tripleAttackCheckBox.setBounds(580, 450, 30, 30);
        tripleAttackCheckBox.setOpaque(false);
        add(tripleAttackCheckBox);

        torpedoCheckBox = new JCheckBox();
        torpedoCheckBox.setBounds(820, 450, 30, 30);
        torpedoCheckBox.setOpaque(false);
        add(torpedoCheckBox);

        // Add buttons directly (without panels)
        buyButton = new JButton("Buy and continue");
        buyButton.setFont(new Font("Arial", Font.BOLD, 20));
        buyButton.addActionListener(new BuyButtonListener());
        buyButton.setBounds(370, 520, 260, 50);
        add(buyButton);

        continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 20));
        continueButton.addActionListener(new ContinueButtonListener());
        continueButton.setBounds(370, 590, 260, 50);
        add(continueButton);
    }

    /* Creates a coin label with the specified amount
     * Uses the coin image and formats the text - Jason
     */
    public JLabel createCoinLabel(int amount)
    {
        JLabel label = new JLabel(amount + "");
        
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
    }

    /* Handles the Buy and Continue button
     * Purchases selected items and moves to the next panel - Jeet
     */
    class BuyButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            int totalCost = 0;

            if (radarCheckBox.isSelected())
            {
                totalCost += 400;
            }

            if (airstrikeCheckBox.isSelected())
            {
                totalCost += 800;
            }

            if (tripleAttackCheckBox.isSelected())
            {
                totalCost += 400;
            }

            if (torpedoCheckBox.isSelected())
            {
                totalCost += 2000;
            }

            if (totalCost <= coins)
            {
                coins -= totalCost; // update the coins
                cards.show(panelCards, "Attack");
            }
        }
    }

    /* This listener updates the timer text every second
     * It increments the seconds counter and updates the label - Jason
     */
    class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            seconds++;
            timerLabel.setText("Time: " + seconds + "s");
        }
    }

    /* Handles the Continue button
     * Skips purchasing and moves to the next panel - Jeet
     */
    class ContinueButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "YouLose");
        }
    }
}

class YouLosePanel extends JPanel 
{
    private FrenchBattleshipHolder panelCards;
    private CardLayout cards;
    private Image bgImage; // background image to be added later
    private Information info;

    public YouLosePanel(FrenchBattleshipHolder ph, CardLayout cl, Information infoIn) 
    {
        panelCards = ph;
        cards = cl;
        info = infoIn;
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());

        bgImage = info.loadImage("BattleshipBg.jpg");

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.CYAN);
        JLabel titleLabel = new JLabel("You lose!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titlePanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(6, 1, 10, 10));
        contentPanel.setBackground(Color.CYAN);
        
        // html to display the curated message
        String message = "<html><div style='text-align: center; width: 700px;'>" +
            "Due to a combination of factors you seem to have lost. But don't give up, to<br>" +
            "succeed you need to fail, and sometimes you need to fail a lot of times.</div></html>";
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel tipsLabel = new JLabel("Some tips:");
        tipsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tipsLabel.setHorizontalAlignment(JLabel.CENTER);

        String tip1 = "• Play it like a normal game of battleship but with questions";
        String tip2 = "• Save up your money for extra powerful power-ups in the Battle-Shop";
        String tip3 = "• Refer to the help section to get some extra help on answering questions";
        String tip4 = "• Practice! Practice! Practice!";

        JLabel tip1Label = new JLabel(tip1);
        JLabel tip2Label = new JLabel(tip2);
        JLabel tip3Label = new JLabel(tip3);
        JLabel tip4Label = new JLabel(tip4);

        tip1Label.setFont(new Font("Arial", Font.PLAIN, 16));
        tip2Label.setFont(new Font("Arial", Font.PLAIN, 16));
        tip3Label.setFont(new Font("Arial", Font.PLAIN, 16));
        tip4Label.setFont(new Font("Arial", Font.PLAIN, 16));

        tip1Label.setHorizontalAlignment(JLabel.CENTER);
        tip2Label.setHorizontalAlignment(JLabel.CENTER);
        tip3Label.setHorizontalAlignment(JLabel.CENTER);
        tip4Label.setHorizontalAlignment(JLabel.CENTER);

        contentPanel.add(messageLabel);
        contentPanel.add(tipsLabel);
        contentPanel.add(tip1Label);
        contentPanel.add(tip2Label);
        contentPanel.add(tip3Label);
        contentPanel.add(tip4Label);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.CYAN);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.addActionListener(new ExitButtonListener());

        JButton homeButton = new JButton("Go to home");
        homeButton.setFont(new Font("Arial", Font.BOLD, 20));
        homeButton.addActionListener(new HomeButtonListener());

        buttonPanel.add(exitButton);
        buttonPanel.add(homeButton);

        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // draws the image (in progress)
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }

    // exits the program if the user clicks exit. 
    class ExitButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            System.exit(1);
            /// maybe in the future: a rectangle pops up, saying, "are you
            /// sure you want to exit?" so the user doesnt lose progress.
        }
    }

    // listens for home button
    class HomeButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent evt) 
        {
            cards.show(panelCards, "First");
        }
    }
}