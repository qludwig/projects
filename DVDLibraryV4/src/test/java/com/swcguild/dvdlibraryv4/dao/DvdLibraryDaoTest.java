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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Suzanne Ludwig
 */
public class DvdLibraryDaoTest {

    private DvdLibraryDao dao;

    public DvdLibraryDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (DvdLibraryDao) ctx.getBean("dvdLibraryDao");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from dvds");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetDeleteDvd() {
        Dvd dvd = new Dvd();
        dvd.setId(3);
        dvd.setTitle("Kingsman");
        dvd.setMpaaRating("R");
        dvd.setReleaseDate("2015");
        dvd.setDirector("Matthew Vaughn");
        dvd.setStudio("Marv");
        dvd.setNote("Manners Maketh Man");
        dvd.setImgSrc("test.jpg");
        dao.addDvd(dvd);
        Dvd fromDb = dao.getDvdById(dvd.getId());
        assertEquals(dvd, fromDb);
        dao.removeDvd(dvd.getId());
        assertNull(dao.getDvdById(dvd.getId()));
    }

    @Test
    public void testUpdateDvd() {
        Dvd dvd = new Dvd();
        dvd.setId(3);
        dvd.setTitle("Kingsman");
        dvd.setMpaaRating("R");
        dvd.setReleaseDate("2015");
        dvd.setDirector("Matthew Vaughn");
        dvd.setStudio("Marv");
        dvd.setNote("Manners Maketh Man");
        dvd.setImgSrc("test.jpg");
        dao.addDvd(dvd);
        dvd.setDirector("Mark Millar");
        dao.updateDvd(dvd);
        Dvd fromDb = dao.getDvdById(dvd.getId());
        assertEquals(dvd, fromDb);
    }

    @Test
    public void testGetAllDvds() {
        Dvd dvd = new Dvd();
        dvd.setId(3);
        dvd.setTitle("Kingsman");
        dvd.setMpaaRating("R");
        dvd.setReleaseDate("2015");
        dvd.setDirector("Matthew Vaughn");
        dvd.setStudio("Marv");
        dvd.setNote("Manners Maketh Man");
        dvd.setImgSrc("test.jpg");
        dao.addDvd(dvd);
        Dvd dvd2 = new Dvd();
        dvd2.setId(4);
        dvd2.setTitle("Back to the Future");
        dvd2.setMpaaRating("PG");
        dvd2.setReleaseDate("2085");
        dvd2.setDirector("Some Dude");
        dvd2.setStudio("Fox");
        dvd2.setNote("Where we're going we don't need roads!");
        dvd2.setImgSrc("test.jpg");
        dao.addDvd(dvd2);
        List<Dvd> dvdlist = dao.getAllDvds();
        assertEquals(dvdlist.size(), 2);
    }

    @Test
    public void testSearch() {
        Dvd dvd = new Dvd();
        dvd.setId(3);
        dvd.setTitle("Kingsman");
        dvd.setMpaaRating("R");
        dvd.setReleaseDate("2015");
        dvd.setDirector("Matthew Vaughn");
        dvd.setStudio("Marv");
        dvd.setNote("Manners Maketh Man");
        dvd.setImgSrc("test.jpg");
        dao.addDvd(dvd);
        Dvd dvd2 = new Dvd();
        dvd2.setId(4);
        dvd2.setTitle("Back to the Future");
        dvd2.setMpaaRating("PG");
        dvd2.setReleaseDate("2085");
        dvd2.setDirector("Some Dude");
        dvd2.setStudio("Fox");
        dvd2.setNote("Where we're going we don't need roads!");
        dvd2.setImgSrc("test.jpg");
        dao.addDvd(dvd2);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.TITLE, "Kingsman");
        List<Dvd> dvdList = dao.search(criteria);
        assertEquals(1, dvdList.size());
        assertEquals(dvd, dvdList.get(0));
    }

    public class DvdLibraryDaoImpl implements DvdLibraryDao {

        public Dvd addDvd(Dvd dvd) {
            return null;
        }

        public Dvd getDvdById(int id) {
            return null;
        }

        public void removeDvd(int id) {
        }

        public void updateDvd(Dvd dvd) {
        }

        public List<Dvd> getAllDvds() {
            return null;
        }

        public List<Dvd> search(Map<SearchTerm, String> criteria) {
            return null;
        }
    }

}
