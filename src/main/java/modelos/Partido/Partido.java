package modelos.Partido;

import modelos.EnumResultado;
import modelos.Equipo.Equipo;
import modelos.Ronda.Ronda;

public class Partido {
        private Ronda Ronda;

        private Equipo Equipo1 ;
        private int Equipo1cantidadgoles;
        private int Equipo2cantidadgoles;
        private Equipo Equipo2;


        public Partido(Ronda ronda, Equipo equipo1, int equipo1cantidadgoles, int equipo2cantidadgoles, Equipo equipo2) {
                Ronda = ronda;
                Equipo1 = equipo1;
                Equipo1cantidadgoles = equipo1cantidadgoles;
                Equipo2cantidadgoles = equipo2cantidadgoles;
                Equipo2 = equipo2;
        }


        public String getRondaid() {
                return Ronda.getRondaid();
        }

        public Equipo getEquipo1() {
                return Equipo1;
        }

        public void setEquipo1(Equipo equipo1) {
                Equipo1 = equipo1;
        }

        public int getEquipo1cantidadgoles() {
                return Equipo1cantidadgoles;
        }

        public void setEquipo1cantidadgoles(int equipo1cantidadgoles) {
                Equipo1cantidadgoles = equipo1cantidadgoles;
        }

        public int getEquipo2cantidadgoles() {
                return Equipo2cantidadgoles;
        }

        public void setEquipo2cantidadgoles(int equipo2cantidadgoles) {
                Equipo2cantidadgoles = equipo2cantidadgoles;
        }

        public Equipo getEquipo2() {
                return Equipo2;
        }

        public void setEquipo2(Equipo equipo2) {
                Equipo2 = equipo2;
        }

        public String getEquipoid1() {
                return Equipo1.getEquipoid();
        }
        public String getEquipoid2() {
                return Equipo2.getEquipoid();
        }

        public Integer getPartidosenRonda(String rondaid ){
                return 0;
        }
}
