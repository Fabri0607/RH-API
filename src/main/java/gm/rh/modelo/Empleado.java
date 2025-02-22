package gm.rh.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idEmpleado")
    private Integer idEmpleado;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("departamento")
    private String departamento;


    @JsonProperty("sueldo")
    private Double sueldo;

    @Override
    public String toString() {
        return STR."Empleado{idEmpleado=\{idEmpleado}, nombre='\{nombre}\{'\''}, departamento='\{departamento}\{'\''}, sueldo=\{sueldo}\{'}'}";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }
}
