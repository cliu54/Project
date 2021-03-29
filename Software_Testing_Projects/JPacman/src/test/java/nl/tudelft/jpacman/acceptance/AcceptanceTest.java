package nl.tudelft.jpacman.acceptance;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {
    private Launcher launcher;
    private Game game;
    private Player player;
//    private Pellet pellet;

    @BeforeEach
    public void before() {launcher = new Launcher();}

    @AfterEach
    public void after() {launcher.dispose();}

    @Test
    public void moveNoPelletNoScoreIncrease() {
        launcher = launcher.withMapFile("/moveNoPelletNoScoreIncrease.txt");
        launcher.launch();
        game = launcher.getGame();
        game.start();

        // check that we've loaded the correct board – not required but nice
        assertEquals(3, game.getLevel().getBoard().getHeight());
        assertEquals(5, game.getLevel().getBoard().getWidth());
        assertEquals(1, game.getLevel().remainingPellets());

        // given the game has started
        assertTrue(game.isInProgress());

        // and my pacman is next to an empty square
        assertTrue(game.getLevel().isAnyPlayerAlive());
        player = game.getPlayers().get(0);
        Square target = player.getSquare().getSquareAt(Direction.WEST);
        assertTrue(target.getOccupants().isEmpty());

        // when I press arrow key toward that square, my pacman can move to
        // that square and my points remain the same.
        assertTrue(target.isAccessibleTo(player));
        int pointsBeforeMoving = player.getScore();
        game.move(player, Direction.WEST);
        assertEquals(pointsBeforeMoving, player.getScore());
        assertEquals(player.getSquare(), target);
    }

    // one action one rule
    @Test
    public void testSuspendGame(){
        launcher = launcher.withMapFile("/moveThePlayer.txt");
        launcher.launch();
        game = launcher.getGame();
        game.start();

        // check that we've loaded the correct board – not required but nice
        assertEquals(3, game.getLevel().getBoard().getHeight());
        assertEquals(5, game.getLevel().getBoard().getWidth());
        assertEquals(1, game.getLevel().remainingPellets());

        // given the game has started
        assertTrue(game.isInProgress());

        // press stop button
        game.stop();

        // check if process got interrupted
        assertFalse(game.isInProgress());
    }

    @Test
    public void testConsumeGame(){
        launcher = launcher.withMapFile("/moveThePlayer.txt");
        launcher.launch();
        game = launcher.getGame();
        game.start();

        // check that we've loaded the correct board – not required but nice
        assertEquals(3, game.getLevel().getBoard().getHeight());
        assertEquals(5, game.getLevel().getBoard().getWidth());
        assertEquals(1, game.getLevel().remainingPellets());

        // given the game has started
        assertTrue(game.isInProgress());

        // check my pacman is next to a Pellet square
        assertTrue(game.getLevel().isAnyPlayerAlive());
        player = game.getPlayers().get(0);
        Square target = player.getSquare().getSquareAt(Direction.WEST);
        assertTrue(target.getOccupants().get(0) instanceof Pellet);

        // press arrow key toward square
        assertTrue(target.isAccessibleTo(player));
        game.move(player, Direction.WEST);

        // check earning point
        assertThat(player.getScore()).isEqualTo(10);
        assertEquals(player.getSquare(), target);

        // check pellet disappears
        int size =  target.getOccupants().size();
        for (int i = 0; i<= size - 1; i++)
            assertFalse(target.getOccupants().get(i) instanceof Pellet);
    }

    @Test
    public void testPlayerTrapped(){
        launcher = launcher.withMapFile("/playerTrapped.txt");
        launcher.launch();
        game = launcher.getGame();
        game.start();

        // check that we've loaded the correct board – not required but nice
        assertEquals(3, game.getLevel().getBoard().getHeight());
        assertEquals(5, game.getLevel().getBoard().getWidth());
        assertEquals(1, game.getLevel().remainingPellets());

        // given the game has started
        assertTrue(game.isInProgress());

        // check my pacman is next to a Pellet square
        assertTrue(game.getLevel().isAnyPlayerAlive());
        player = game.getPlayers().get(0);

        // move all the directions
        game.move(player, Direction.EAST);
        Square target1 = player.getSquare().getSquareAt(Direction.WEST);
        assertFalse(target1.isAccessibleTo(player));

        game.move(player, Direction.WEST);
        Square target2 = player.getSquare().getSquareAt(Direction.WEST);
        assertFalse(target2.isAccessibleTo(player));

        game.move(player, Direction.NORTH);
        Square target3 = player.getSquare().getSquareAt(Direction.WEST);
        assertFalse(target3.isAccessibleTo(player));

        game.move(player, Direction.SOUTH);
        Square target4 = player.getSquare().getSquareAt(Direction.WEST);
        assertFalse(target4.isAccessibleTo(player));

    }

    @Test
    public void testPlayerDead() throws InterruptedException {
        launcher = launcher.withMapFile("/playerDead.txt");
        launcher.launch();
        game = launcher.getGame();
        game.start();

        // check that we've loaded the correct board – not required but nice
        assertEquals(3, game.getLevel().getBoard().getHeight());
        assertEquals(5, game.getLevel().getBoard().getWidth());

        // check player next to the ghost
        assertTrue(game.getLevel().isAnyPlayerAlive());
        player = game.getPlayers().get(0);
        Square target = player.getSquare().getSquareAt(Direction.WEST);
        assertTrue(target.getOccupants().get(0) instanceof Ghost);

        // press array key and move toward ghost
        assertTrue(target.isAccessibleTo(player));

        // check whether player dies
        Thread.sleep(500L);

        game.move(player,Direction.WEST);
        assertFalse(game.getLevel().isAnyPlayerAlive());


        // check if game is over
        game.stop();
        assertFalse(game.isInProgress());
    }

    public static void move(Game game, Direction dir, int numSteps) {
        Player player = game.getPlayers().get(0);
        for (int i = 0; i < numSteps; i++) {
            game.move(player, dir);
        }
    }
}
