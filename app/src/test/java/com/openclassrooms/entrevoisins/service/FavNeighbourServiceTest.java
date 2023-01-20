package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class FavNeighbourServiceTest {

    private NeighbourApiService service;
    private Neighbour neighbour;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void addFavNeighbourWithSuccess() {
        Neighbour neighbourToAdd = service.getNeighbours().get(0);
        service.addFavNeighbour(neighbourToAdd);
        assertTrue(service.getFavNeighbours().contains(neighbourToAdd));
    }

    @Test
    public void getFavNeighboursWithSuccess() {
        Neighbour neighbourToAdd = service.getNeighbours().get(0);
        service.addFavNeighbour(neighbourToAdd);
        List<Neighbour> neighbours = service.getFavNeighbours();
        assertEquals(1, neighbours.size());
    }

    @Test
    public void checkIsFavNeighbourWithSuccess() {
        Neighbour neighbourToAdd = service.getNeighbours().get(0);
        service.addFavNeighbour(neighbourToAdd);
        assertTrue(service.checkIsFavNeighbour(neighbourToAdd));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToAdd = service.getNeighbours().get(0);
        service.addFavNeighbour(neighbourToAdd);
        Neighbour neighbourToDelete = service.getFavNeighbours().get(0);
        service.deleteFavNeighbour(neighbourToDelete);
        assertFalse(service.getFavNeighbours().contains(neighbourToDelete));
    }
}
