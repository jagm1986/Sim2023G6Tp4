package ObjetosPermanentes;

import enums.Estado;
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
public class CajaCobro {

    private int id;
    private Estado estadoCaja;
    private int cola;


}
