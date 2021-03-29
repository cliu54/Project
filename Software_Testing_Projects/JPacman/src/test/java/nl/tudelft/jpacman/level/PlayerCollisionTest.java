package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

public class PlayerCollisionTest {
    PlayerCollisions cmap;
    PointCalculator pc;
    PlayerCollisions mocked_cmap;
    Unit player;
    Unit pellet;
    Unit ghost;

    @BeforeEach
    public void setUp() {
        pc = mock(PointCalculator.class);
        cmap = new PlayerCollisions(pc);
        mocked_cmap = mock(PlayerCollisions.class);
        player = mock(Player.class);
        pellet = mock(Pellet.class);
        ghost = mock(Ghost.class);

    }

    @Test
    public void testPlayerCollidesPellet(){
        cmap.collide(player, pellet);
        verify(pc).consumedAPellet((Player) player, (Pellet) pellet);
    }

    @Test
    public void testPlayerCollidesGhost(){
        cmap.collide(player, ghost);
        verify(pc).collidedWithAGhost((Player) player, (Ghost) ghost);
    }

    /**
     *  The following four tests are for the no outcomes cases
     */

    @Test
    public void testPlayerCollidesPlayer(){
        cmap.collide(player,player);
        verifyNoMoreInteractions(pc);
    }

    @Test
    public void testPelletCollidesPellet(){
        cmap.collide(pellet,pellet);
        verifyNoMoreInteractions(pc);
    }

    @Test
    public void testGhostCollidesGhost(){
        cmap.collide(ghost,ghost);
        verifyNoMoreInteractions(pc);
    }

    @Test
    public void testPelletCollidesGhost(){
        cmap.collide(pellet,ghost);
        verifyNoMoreInteractions(pc);
    }
}

