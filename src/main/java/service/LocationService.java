package service;

import dao.LocationDao;
import model.Location;

import java.util.List;

public class LocationService {
    private final LocationDao locationDao = new LocationDao();
    public void save(Location location){
        locationDao.save(location);
    }
    public void remove(Location location){
        locationDao.remove(location);
    }
    public Location findByCityAndUserId(String name, int id){
        return locationDao.findByCityAndUserId(name, id);
    }
    public List<Location> findUserId(int id){
        return locationDao.findUserId(id);
    }
}
