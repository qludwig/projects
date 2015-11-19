package com.swcguild.dvdlibraryv4.dao;

import com.swcguild.dvdlibraryv4.model.Dvd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Suzanne Ludwig
 */
public class DvdLibraryDaoImpl implements DvdLibraryDao {

    private Map<Integer, Dvd> dvdMap = new HashMap<>();
    private static int idCounter = 0;

    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setId(idCounter);
        idCounter++;
        dvdMap.put(dvd.getId(), dvd);
        return dvd;
    }

    @Override
    public Dvd getDvdById(int id) {
        return dvdMap.get(id);
    }

    @Override
    public void removeDvd(int id) {
        dvdMap.remove(id);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.put(dvd.getId(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> all = dvdMap.values();
        return new ArrayList(all);
    }

    @Override
    public List<Dvd> search(Map<SearchTerm, String> criteria) {
        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String releaseDateCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        String mpaaRatingCriteria = criteria.get(SearchTerm.MPAA_RATING);
        String directorCriteria = criteria.get(SearchTerm.DIRECTOR);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);

        Predicate<Dvd> titleMatches;
        Predicate<Dvd> releaseDateMatches;
        Predicate<Dvd> mpaaRatingMatches;
        Predicate<Dvd> directorMatches;
        Predicate<Dvd> studioMatches;

        Predicate<Dvd> truePredicate = (c) -> {
            return true;
        };

        titleMatches = (titleCriteria == null || titleCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getTitle().equals(titleCriteria);
        releaseDateMatches = (releaseDateCriteria == null || releaseDateCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getReleaseDate().equals(releaseDateCriteria);
        mpaaRatingMatches = (mpaaRatingCriteria == null || mpaaRatingCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getMpaaRating().equals(mpaaRatingCriteria);
        directorMatches = (directorCriteria == null || directorCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getDirector().equals(directorCriteria);
        studioMatches = (studioCriteria == null || studioCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getStudio().equals(studioCriteria);
// Return the list of Contacts that match the given criteria. To do this we
// just AND all the predicates together in a filter operation.
        return dvdMap.values().stream()
                .filter(titleMatches
                        .and(releaseDateMatches)
                        .and(mpaaRatingMatches)
                        .and(directorMatches)
                        .and(studioMatches))
                .collect(Collectors.toList());
    }
}
