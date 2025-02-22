package gm.rh.controlador;

import gm.rh.exepcion.RecursoNoEncontradoExcepcion;
import gm.rh.modelo.Empleado;
import gm.rh.servicio.EmpleadoServicio;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {
    private static final Logger log = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @GetMapping(value = "/empleados", produces = "application/json")
    public List<Empleado> obtenerEmpleados() {
        var empleados = empleadoServicio.listarEmpleados();
        empleados.forEach(empleado -> log.info(empleado.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        log.info("Empleado a guardar: {}", empleado);
        return empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el empleado con el id: " + id);
        }
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        Empleado empleadoActual = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleadoActual == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el empleado con el id: " + id);
        }
        empleadoActual.setNombre(empleado.getNombre());
        empleadoActual.setDepartamento(empleado.getDepartamento());
        empleadoActual.setSueldo(empleado.getSueldo());
        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el empleado con el id: " + id);
        }
        empleadoServicio.eliminarEmpleado(empleado);
        Map<String, Boolean> response = new HashMap();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
