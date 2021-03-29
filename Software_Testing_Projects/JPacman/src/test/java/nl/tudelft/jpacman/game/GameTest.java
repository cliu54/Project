package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class GameTest
{
    // initialization
    private Player mockedPlayer = mock(Player.class);
    private PlayerFactory playerFactory = mock(PlayerFactory.class);
    private Game mockedGame;
    private GameFactory mockedGameFactory = new GameFactory(playerFactory);
    private Level mockedLevel = mock(Level.class);
    private PointCalculator mockedPointCalculator = mock(PointCalculator.class);


    @Test
    public void GameStartTest(){
        when(mockedLevel.isAnyPlayerAlive()).thenReturn(true);
        when(mockedLevel.remainingPellets()).thenReturn(4);
        when(playerFactory.createPacMan()).thenReturn(mockedPlayer);
        mockedGame = mockedGameFactory.createSinglePlayerGame(mockedLevel,mockedPointCalculator);
        mockedGame.start();
        assertThat(mockedGame.isInProgress()).isEqualTo(true);
    }

    @Test
    public void GameInProgress(){
        when(mockedLevel.isAnyPlayerAlive()).thenReturn(true);
        when(mockedLevel.remainingPellets()).thenReturn(4);
        when(playerFactory.createPacMan()).thenReturn(mockedPlayer);
        mockedGame = mockedGameFactory.createSinglePlayerGame(mockedLevel,mockedPointCalculator);
        mockedGame.start();
        mockedGame.start();
        mockedGame.start();
        mockedGame.start();
        assertThat(mockedGame.isInProgress()).isEqualTo(true);
    }

    @Test
    public void EndOfGame(){
        when(mockedLevel.isAnyPlayerAlive()).thenReturn(false);
        when(mockedLevel.remainingPellets()).thenReturn(4);
        when(playerFactory.createPacMan()).thenReturn(mockedPlayer);
        mockedGame = mockedGameFactory.createSinglePlayerGame(mockedLevel,mockedPointCalculator);
        mockedGame.start();
        assertThat(mockedGame.isInProgress()).isEqualTo(false);
    }
}
