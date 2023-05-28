
package ObjetosTemporales
        ;

import ObjetosPermanentes.Sector;
import enums.EstadoAuto;
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
public class Auto 
{
    private int id;
    private EstadoAuto estadoAuto;
    private double precioXMinutos;
    private double horaEntradaCobro;
    private int nroFinEstacionamiento;
    
    // Usage of comparator
    public static Comparator<Auto> AutoComparator = new Comparator<Auto>() {
 
        // Comparing attributes of students
        public int compare(Auto s1, Auto s2) {
            Integer aId
                = s1.id;
            Integer aId2
                = s2.id;
 
            // Returning in ascending order
            return aId.compareTo(
                       aId2);
 
        }
    };
  
}
