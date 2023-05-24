
package ObjetosTemporales
        ;

import enums.EstadoAuto;
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
    
  
}
