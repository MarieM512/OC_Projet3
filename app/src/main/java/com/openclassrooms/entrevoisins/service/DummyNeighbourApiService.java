package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private final ArrayList<Neighbour> favoriteList = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Neighbour> getFavNeighbours() {
        return favoriteList;
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void addFavNeighbour(Neighbour neighbour) {
        favoriteList.add(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void deleteFavNeighbour(Neighbour neighbour) {
        favoriteList.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public boolean checkIsFavNeighbour(Neighbour neighbour) {
        return (favoriteList.contains(neighbour));
    }
}
