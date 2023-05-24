package ObjetosPermanentes;

import enums.Estado;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sector {

    private int id;
    private Estado estadoSector;

// Usage of comparator
    public static Comparator<Sector> SectorComparator = new Comparator<Sector>() {
 
        // Comparing attributes of students
        public int compare(Sector s1, Sector s2) {
            Integer sectorId
                = s1.id;
            Integer sectorId2
                = s2.id;
 
            // Returning in ascending order
            return sectorId.compareTo(
                       sectorId2);
 
        }
    };
}
