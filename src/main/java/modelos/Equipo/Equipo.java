package modelos.Equipo;

public class Equipo {

        private String equipoid;
        private String nombre;

        public Equipo(String equipoid, String nombre, String descripcion) {
                this.equipoid = equipoid;
                this.nombre = nombre;
                this.descripcion = descripcion;
        }

        private String descripcion;

        public String getEquipoid() {
                return equipoid;
        }

        public void setEquipoid(String equipoid) {
                this.equipoid = equipoid;
        }

        public String getNombre() {
                return nombre;
        }

        public void setNombre(String nombre) {
                this.nombre = nombre;
        }

        public String getDescripcion() {
                return descripcion;
        }

        public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
        }
}
