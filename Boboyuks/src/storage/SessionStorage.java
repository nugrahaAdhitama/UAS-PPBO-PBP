package storage;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JFrame;

public class SessionStorage {
    private static int userId;
    private static boolean isLoggedIn = false;
    private static ResultSet hotelSearchResult;
    private static String searchQuery;
    private static int hotelId;
    private static JFrame currentFrame;
    
    public static HashMap<String, String> searchData = new HashMap<>();
//    public static ArrayList appliedFacilityFilters = new ArrayList<>();
    public static HashMap<String, Integer> appliedFacilityFilters = new HashMap<>() {
        @Override
        public Integer get(Object key) {
            Integer value = super.get(key);
            if (value == null) return 0;
            return value;
        };
    };
    
    public static HashMap<String, String> bookedHotelData = new HashMap<>();
    public static boolean isAppliedFacilityFilters = false;
    
    public static int getUserId() {
        return userId;
    }
    
    public static void setUserId(int newUserId) {
        userId = newUserId;
    }
    
    public static boolean getLoginStatus() {
        return isLoggedIn;
    }
    
    public static void setLoginStatus(boolean status) {
        isLoggedIn = status;
    }
    
    public static ResultSet getHotelSearchResult() {
        return hotelSearchResult;
    }
    
    public static void setHotelSearchResult(ResultSet result) {
        hotelSearchResult = result;
    }
    
    public static String getSearchQuery() {
        return searchQuery;
    }
    
    public static void setSearchQuery(String search) {
        searchQuery = search;
    }
    
    public static int getHotelId() {
        return hotelId;
    }
    
    public static void setHotelId(int idHotel) {
        hotelId = idHotel;
    }
    
    public static JFrame getCurrentFrame() {
        return currentFrame;
    }
    
    public static void setCurrentFrame(JFrame frame) {
        currentFrame = frame;
    }
}
