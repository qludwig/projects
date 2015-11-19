/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibraryv4.dao;

import com.swcguild.dvdlibraryv4.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Suzanne Ludwig
 */
public interface DvdLibraryDao {

    public Dvd addDvd(Dvd dvd);
    public Dvd getDvdById(int id);
    public void removeDvd(int id);
    public void updateDvd(Dvd dvd);
    public List<Dvd> getAllDvds();
    public List<Dvd> search(Map<SearchTerm, String> criteria);
}
