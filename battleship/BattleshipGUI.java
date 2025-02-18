/**
 * @file BattleshipGUI.java
 *   Main GUI class for the Battleship game.
 */

package battleship;

import javax.swing.*;

import battleship.factorys.gameboard.IGameBoard;
import battleship.factorys.gameboard.PlayerBoardFactory;
import battleship.factorys.gameboard.TargetingBoardFactory;
import battleship.factorys.player.IPlayer;
import battleship.factorys.player.LocalPlayerFactory;
import battleship.factorys.ships.*;
import battleship.views.MainMenuView;
import battleship.views.PlacementView;
import battleship.views.ShootingView;

import java.awt.*;

/**
 * @class BattleshipGUI
 *        Main GUI class for the Battleship game.
 *        Extends {@link JFrame} to create the main window for the game.
 */
public class BattleshipGUI extends JFrame {

    private CardLayout cardLayout;
    /** < The card layout for switching views */
    private JPanel panelCont;
    /** < The main container panel */

    private IGameBoard player1Board;
    /** < Game board for player 1 */
    private IGameBoard player1TargetingBoard;
    /** < Targeting board for player 1 */
    private IGameBoard player2Board;
    /** < Game board for player 2 */
    private IGameBoard player2TargetingBoard;
    /** < Targeting board for player 2 */
    private IPlayer player1;
    /** < The first player */
    private IPlayer player2;

    /** < The second player */

    /**
     * Constructor for BattleshipGUI.
     * Initializes the main window and starts the local coop game.
     */
    public BattleshipGUI() {
        super("Battleship Game");
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        cardLayout = new CardLayout();
        panelCont = new JPanel(cardLayout);

        panelCont.add(new MainMenuView(cardLayout, panelCont), "MainMenuView");

        add(panelCont);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1400, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Shows the ship placement view.
     * Ensures players are initialized before showing the placement view.
     */
    public void showPlacementView() {
        if (player1 == null || player2 == null) {
            System.err.println("Players are not initialized. Call initializeLocalCoopGame first.");
            return;
        }

        PlacementView placementView = new PlacementView(
                cardLayout,
                panelCont,
                player1Board,
                player2Board,
                player1,
                player2,
                this);
        panelCont.add(placementView, "PlacementView");
        cardLayout.show(panelCont, "PlacementView");
    }

    /**
     * Shows the shooting view.
     * Ensures players are initialized before showing the shooting view.
     */
    public void showShootingView() {
        if (player1 == null || player2 == null) {
            System.err.println("Players are not initialized. Call initializeLocalCoopGame first.");
            return;
        }

        ShootingView shootingView = new ShootingView(player1, player2);
        panelCont.add(shootingView, "ShootingView");
        cardLayout.show(panelCont, "ShootingView");
    }

    /**
     * Initializes the local coop game.
     * Creates players and their respective game boards and targeting boards.
     */
    public void initializeLocalCoopGame() {
        // Spieler erstellen
        player1 = new LocalPlayerFactory().createPlayer("Spieler 1");
        player2 = new LocalPlayerFactory().createPlayer("Spieler 2");

        // Spielbretter erstellen
        player1Board = new PlayerBoardFactory().createGameBoard();
        player1TargetingBoard = new TargetingBoardFactory().createGameBoard();
        player2Board = new PlayerBoardFactory().createGameBoard();
        player2TargetingBoard = new TargetingBoardFactory().createGameBoard();

        player1.setGameBoard(player1Board);
        player2.setGameBoard(player2Board);
        player1.setTargetingBoard(player1TargetingBoard);
        player2.setTargetingBoard(player2TargetingBoard);
    }

        public void initializeDebugGame() {
        // Spieler erstellen
        player1 = new LocalPlayerFactory().createPlayer("Spieler 1");
        player2 = new LocalPlayerFactory().createPlayer("Spieler 2");
    
        // Spielbretter erstellen
        player1Board = new PlayerBoardFactory().createGameBoard();
        player1TargetingBoard = new TargetingBoardFactory().createGameBoard();
        player2Board = new PlayerBoardFactory().createGameBoard();
        player2TargetingBoard = new TargetingBoardFactory().createGameBoard();
    
        player1.setGameBoard(player1Board);
        player2.setGameBoard(player2Board);
        player1.setTargetingBoard(player1TargetingBoard);
        player2.setTargetingBoard(player2TargetingBoard);
    
        // SchlachtschiffFactory instanziieren
        SchlachtschiffFactory schlachtschiffFactory = new SchlachtschiffFactory();
        ZerstörerFactory zerstörerFactory = new ZerstörerFactory();

        KreuzerFactory kreuzerFactory = new KreuzerFactory();
        U_BootFactory u_BootFactory = new U_BootFactory();

    
        // Schiff platzieren
        player1.getGameBoard().placeShip(0, 0, schlachtschiffFactory.createShip(), true);
        player1.getGameBoard().placeShip(0, 2, zerstörerFactory.createShip(), true);
        player1.getGameBoard().placeShip(0, 4, zerstörerFactory.createShip(), true);
        player1.getGameBoard().placeShip(0, 6, kreuzerFactory.createShip(), true);
        player1.getGameBoard().placeShip(0, 8, kreuzerFactory.createShip(), true);
        player1.getGameBoard().placeShip(6, 0, kreuzerFactory.createShip(), true);
        player1.getGameBoard().placeShip(6, 2, u_BootFactory.createShip(), true);
        player1.getGameBoard().placeShip(6, 4, u_BootFactory.createShip(), true);
        player1.getGameBoard().placeShip(6, 6, u_BootFactory.createShip(), true);
        player1.getGameBoard().placeShip(6, 8, u_BootFactory.createShip(), true);

        player2.getGameBoard().placeShip(0, 0, schlachtschiffFactory.createShip(), true);
        player2.getGameBoard().placeShip(0, 2, zerstörerFactory.createShip(), true);
        player2.getGameBoard().placeShip(0, 4, zerstörerFactory.createShip(), true);
        player2.getGameBoard().placeShip(0, 6, kreuzerFactory.createShip(), true);
        player2.getGameBoard().placeShip(0, 8, kreuzerFactory.createShip(), true);
        player2.getGameBoard().placeShip(6, 0, kreuzerFactory.createShip(), true);
        player2.getGameBoard().placeShip(6, 2, u_BootFactory.createShip(), true);
        player2.getGameBoard().placeShip(6, 4, u_BootFactory.createShip(), true);
        player2.getGameBoard().placeShip(6, 6, u_BootFactory.createShip(), true);
        player2.getGameBoard().placeShip(6, 8, u_BootFactory.createShip(), true);
        

    }

}