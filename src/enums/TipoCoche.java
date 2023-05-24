/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author 
 */
public enum TipoCoche {
    CHICO(0),
    GRANDE(1),
    UTILITARIO(2),
    NONE(3);
    
    private final int value;
    
    TipoCoche(int value) {
        this.value = value;
    }

   public static Optional<TipoCoche> valueOf(int value) {
        return Arrays.stream(values())
            .filter(legNo -> legNo.value == value)
            .findFirst();
    }
}
