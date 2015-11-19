

package com.swcguild.dvdlibraryv4.controller;

import com.swcguild.dvdlibraryv4.dao.DvdLibraryDao;
import com.swcguild.dvdlibraryv4.model.Dvd;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Suzanne Ludwig
 */
@Controller
public class HomeController {
    
    private DvdLibraryDao dao;
    
    @Inject
    public HomeController(DvdLibraryDao dao) {
        this.dao=dao;
    }
    
    @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }
    
    @RequestMapping(value="/dvd", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Dvd addDvd(@Valid @RequestBody Dvd dvd) {
        dao.addDvd(dvd);
        return dvd;
    }
    
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.GET) 
    @ResponseBody
    public Dvd readDvd(@PathVariable("id") int id) {
        return dao.getDvdById(id);
    }
    
    @RequestMapping(value="/dvds", method=RequestMethod.GET) 
    @ResponseBody
    public List<Dvd> readAllDvds() {
        return dao.getAllDvds();
    }
    
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDvd(@PathVariable("id") int id, @Valid @RequestBody Dvd dvd) {
        dvd.setId(id);
        dao.updateDvd(dvd);
    }
    
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") int id) {
        dao.removeDvd(id);
    }
    
}
