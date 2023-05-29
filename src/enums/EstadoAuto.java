/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author
 */
public enum EstadoAuto {
    ESPERANDO_COBRO {

        @Override
        public EstadoAuto setGetValue(String yourValue) {
            return ESPERANDO_COBRO;
        }
    },
    SIENDO_COBRADO {

        @Override
        public EstadoAuto setGetValue(String yourValue) {
            return SIENDO_COBRADO;
        }
    },
    ELIMINADO {

        @Override
        public EstadoAuto setGetValue(String yourValue) {
            return ELIMINADO;
        }
    },
    NO_INGRESADO {

        @Override
        public EstadoAuto setGetValue(String yourValue) {
            return NO_INGRESADO;
        }
    },
    ESTACIONADO {

        @Override
        public EstadoAuto setGetValue(String yourValue) {
            EstadoAuto estado = EstadoAuto.ESTACIONADO;
            switch (yourValue) {
                case "1":
                    estado = EstadoAuto.ESTACIONADO_1;
                    break;
                case "2":
                    estado = EstadoAuto.ESTACIONADO_2;
                    break;
                case "3":
                    estado = EstadoAuto.ESTACIONADO_3;
                    break;
                case "4":
                    estado = EstadoAuto.ESTACIONADO_4;
                    break;
                case "5":
                    estado = EstadoAuto.ESTACIONADO_5;
                    break;
                case "6":
                    estado = EstadoAuto.ESTACIONADO_6;
                    break;
                case "7":
                    estado = EstadoAuto.ESTACIONADO_7;
                    break;
                case "8":
                    estado = EstadoAuto.ESTACIONADO_8;
                    break;
                case "9":
                    estado = EstadoAuto.ESTACIONADO_9;
                    break;
                case "10":
                    estado = EstadoAuto.ESTACIONADO_10;
                    break;
                default:

            }
            return estado;
        }
    }, ESTACIONADO_1 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }, ESTACIONADO_2 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }, ESTACIONADO_3 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }, ESTACIONADO_4 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }, ESTACIONADO_5 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }, ESTACIONADO_6 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }, ESTACIONADO_7 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }, ESTACIONADO_8 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }, ESTACIONADO_9 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }, ESTACIONADO_10 {
        @Override
        public EstadoAuto setGetValue(String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };

    public abstract EstadoAuto setGetValue(String value);
}
