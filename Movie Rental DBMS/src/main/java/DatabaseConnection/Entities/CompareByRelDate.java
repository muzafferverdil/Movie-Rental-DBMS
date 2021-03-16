package DatabaseConnection.Entities;

import java.util.Comparator;

public class CompareByRelDate implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        if(o1.getRelDate().compareTo(o2.getRelDate())>0){
            return -1;
        }
        else if(o1.getRelDate().compareTo(o2.getRelDate())<0){
            return +1;
        }
        else {
            return 0;
        }
    }
}
