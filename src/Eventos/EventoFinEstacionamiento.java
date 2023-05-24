package Eventos;

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
public class EventoFinEstacionamiento {

    private int nro;
    private long tiempoEstacionamiento;
    private double finEstacionamiento;
    private int cantidadOcupados;

    // Usage of comparator
    public static Comparator<EventoFinEstacionamiento> FinEstacionamientoComparator = new Comparator<EventoFinEstacionamiento>() {
 
        // Comparing attributes of students
        public int compare(EventoFinEstacionamiento s1, EventoFinEstacionamiento s2) {
            Integer finEstId
                = s1.nro;
            Integer finEstId2
                = s2.nro;
 
            // Returning in ascending order
            return finEstId.compareTo(
                       finEstId2);
 
        }
    };
    


}
