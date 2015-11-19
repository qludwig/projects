

package com.swcguild.dvdlibraryv4.dao;

import com.swcguild.dvdlibraryv4.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Suzanne Ludwig
 */
public class DvdLibraryDaoDbImpl implements DvdLibraryDao {

    private static final String SQL_INSERT_DVD = 
            "insert into dvds (title, release_date, mpaa_rating, director, studio, note, img_src) values (?,?,?,?,?,?,?)";
    
    private static final String SQL_DELETE_DVD =
            "delete from dvds where dvd_id = ?";
    
    private static final String SQL_SELECT_DVD =
            "select * from dvds where dvd_id = ?";
    
    private static final String SQL_SELECT_ALL_DVDS =
            "select * from dvds";
    
    private static final String SQL_UPDATE_DVD =
            "update dvds set title = ?, release_date = ?, mpaa_rating = ?, director = ?, studio = ?, note = ?, img_src = ? where dvd_id = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getReleaseDate(),
                dvd.getMpaaRating(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getNote(),
                dvd.getImgSrc());
        dvd.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return dvd;
    }
            
    public Dvd getDvdById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD, new DvdMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
            
    public void removeDvd(int id) {
        jdbcTemplate.update(SQL_DELETE_DVD, id);
    }
            
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD, 
                dvd.getTitle(),
                dvd.getReleaseDate(),
                dvd.getMpaaRating(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getNote(),
                dvd.getImgSrc(),
                dvd.getId());
    }
            
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DvdMapper());
    }
            
    public List<Dvd> search(Map<SearchTerm, String> criteria) {
        if (criteria.size() == 0) {
            return getAllDvds();
        } else {
            StringBuilder sQuery = new StringBuilder("select * from dvds where ");
            
            int numParams = criteria.size();
            int paramPosition = 0;
            String[] paramVals = new String[numParams];
            
            Set<SearchTerm> keyset = criteria.keySet();
            Iterator<SearchTerm> iter = keyset.iterator();
            
            while(iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }
            return jdbcTemplate.query(sQuery.toString(), new DvdMapper(), paramVals);
        }
    }
    
    private static final class DvdMapper implements ParameterizedRowMapper<Dvd> {
        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setId(rs.getInt("dvd_id"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseDate(rs.getString("release_date"));
            dvd.setMpaaRating(rs.getString("mpaa_rating"));
            dvd.setDirector(rs.getString("director"));
            dvd.setStudio(rs.getString("studio"));
            dvd.setNote(rs.getString("note"));
            dvd.setImgSrc(rs.getString("img_src"));
            return dvd;
        }
    }
            
}
