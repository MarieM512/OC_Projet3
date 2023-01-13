package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get all my Favorite Neighbours
     * @return {@link List}
     */
    ArrayList<Neighbour> getFavNeighbours();

    /**
     * Add a favorite neighbour
     * @param neighbour
     */
    void addFavNeighbour(Neighbour neighbour);

    /**
     * Deletes a favorite neighbour
     * @param neighbour
     */
    void deleteFavNeighbour(Neighbour neighbour);

    /**
     * Check if neighbour is in favorite
     * @param neighbour
     * @return
     */
    boolean checkFavNeighbour(Neighbour neighbour);
}
