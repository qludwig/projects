

package com.swcguild.dvdlibraryv4.controller;

import com.swcguild.dvdlibraryv4.dao.DvdLibraryDao;
import com.swcguild.dvdlibraryv4.dao.SearchTerm;
import com.swcguild.dvdlibraryv4.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author Suzanne Ludwig
 */
@Controller
public class SearchController {
    
    private DvdLibraryDao dao;
    
    @Inject
    public SearchController(DvdLibraryDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value="/search", method=RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }
    
    @RequestMapping(value="search/dvds", method=RequestMethod.POST)
    @ResponseBody
    public List<Dvd> searchDvds(@RequestBody Map<String, String> searchmap) {
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        String currentTerm;
        
        currentTerm = searchmap.get("title");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.TITLE, currentTerm);
        }
        currentTerm = searchmap.get("releaseDate");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.RELEASE_DATE, currentTerm);
        }
        currentTerm = searchmap.get("mpaaRating");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.MPAA_RATING, currentTerm);
        }
        currentTerm = searchmap.get("director");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DIRECTOR, currentTerm);
        }
        currentTerm = searchmap.get("studio");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.STUDIO, currentTerm);
        }
        
        return dao.search(criteriaMap);
    }
}
