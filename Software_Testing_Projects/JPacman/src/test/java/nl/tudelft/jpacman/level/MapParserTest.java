package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;

import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MapParserTest {
   LevelFactory mockedLevelFactory = mock(LevelFactory.class);
   BoardFactory mockedBoardFactory = mock(BoardFactory.class);
   Pellet mockedPellet = mock(Pellet.class);
   MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);
   Ghost mockedGhost = mock(Ghost.class);
   Square mockedSquare = mock(Square.class);

//   MapParser mockedMapParser = mock(MapParser.class);

    //LevelFactory levelCreator = mock(LevelFactory.class);

    @Test
    public void testSpace()
    {
        mapParser.parseMap(List.of(" "));
        verify(mockedBoardFactory).createGround();
        verify(mockedBoardFactory).createBoard(any());
        verify(mockedLevelFactory).createLevel(any(),any(),any());

    }
    //logging
//        mockedBoardFactory = mock(BoardFactory.class, withSettings().verboseLogging());
//        mapParser.parseMap(List.of(" "));
    // stubbing
//    levelCreator.createPellet().occupy(pelletSquare);
//    Pellet mockedPellet = mock(Pellet.class);
//    when(mockedLevelFactory.createPellet()).thenReturn(mockedPellet);

    @Test
    public void testWall()
    {
        mapParser.parseMap(List.of("#"));
        verify(mockedBoardFactory, times(1)).createWall();
        verify(mockedLevelFactory, times(1)).createLevel(any(), anyList(), anyList());
        verify(mockedBoardFactory, times(1)).createBoard(any());
    }
    @Test
    public void testPlayerAndSpace()
    {
        mapParser.parseMap(List.of("P "));
        verify(mockedBoardFactory,times(2)).createGround();
    }

    @Test
    public void testPellet()
    {
        when(mockedLevelFactory.createPellet()).thenReturn(mockedPellet);
        mapParser.parseMap(List.of("."));
        verify(mockedLevelFactory).createPellet();
    }

    @Test
    public void testGhost()
    {

        when(mockedLevelFactory.createGhost()).thenReturn(mockedGhost);
        mapParser.parseMap(List.of("G"));
        verify(mockedLevelFactory).createGhost();
    }

    @Test
    public void testNoOtherMethod()
    {
        mapParser.parseMap(List.of(" "));
        verify(mockedBoardFactory).createGround();
        verify(mockedBoardFactory).createBoard(any());
        verify(mockedLevelFactory).createLevel(any(),any(),any());

        verifyNoMoreInteractions(mockedBoardFactory);
    }

    @Test
    public void badWeatherTestEmptyParameter()
    {
        assertThrows(PacmanConfigurationException.class , ()->{ mapParser.parseMap(List.of("")); });

    }
    @Test
    public void badWeatherTestNoPath()
    {
        assertThrows(PacmanConfigurationException.class , ()->{ mapParser.parseMap(List.of("##","   ")); });
    }

    @Test
    void badWeatherTestInvalidSysmbol()
    {
        assertThrows(PacmanConfigurationException.class , ()->{ mapParser.parseMap(List.of("!")); });
    }
}
