package modelos;

import java.util.List;
import java.util.Optional;

public class Resultado {
        private String Rondaid;
        private String Rondanro ;
        private String Equipo1id ;
        private String Equipo1nombre;
        private String Equipo1descripcion;
        private int Equipo1cantidadgoles;
        private int Equipo2cantidadgoles;
        private String Equipo2id;
        private String Equipo2nombre;
        private String Equipo2descripcion;

        public Resultado() {
        }

        public String getRondaid() {
                return Rondaid;
        }

        public void setRondaid(String rondaid) {
                Rondaid = rondaid;
        }

        public String getRondanro() {
                return Rondanro;
        }

        public void setRondanro(String rondanro) {
                Rondanro = rondanro;
        }

        public String getEquipo1id() {
                return Equipo1id;
        }

        public void setEquipo1id(String equipo1id) {
                Equipo1id = equipo1id;
        }

        public String getEquipo1nombre() {
                return Equipo1nombre;
        }

        public void setEquipo1nombre(String equipo1nombre) {
                Equipo1nombre = equipo1nombre;
        }

        public String getEquipo1descripcion() {
                return Equipo1descripcion;
        }

        public void setEquipo1descripcion(String equipo1descripcion) {
                Equipo1descripcion = equipo1descripcion;
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

        public String getEquipo2id() {
                return Equipo2id;
        }

        public void setEquipo2id(String equipo2id) {
                Equipo2id = equipo2id;
        }

        public String getEquipo2nombre() {
                return Equipo2nombre;
        }

        public void setEquipo2nombre(String equipo2nombre) {
                Equipo2nombre = equipo2nombre;
        }

        public String getEquipo2descripcion() {
                return Equipo2descripcion;
        }

        public void setEquipo2descripcion(String equipo2descripcion) {
                Equipo2descripcion = equipo2descripcion;
        }


}
