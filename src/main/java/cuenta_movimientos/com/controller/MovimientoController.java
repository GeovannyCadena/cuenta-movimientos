package cuenta_movimientos.com.controller;

import cuenta_movimientos.com.dto.request.MovimientoDTO;
import cuenta_movimientos.com.dto.response.MovimientoResponseDTO;
import cuenta_movimientos.com.service.interfaces.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@Tag(name = "Movimientos", description = "Operaciones relacionadas con movimientos bancarios")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @Operation(summary = "Crear un nuevo movimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movimiento creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos enviados")
    })
    @PostMapping
    public ResponseEntity<MovimientoResponseDTO> createMovimiento(@Valid @RequestBody MovimientoDTO movimientoDTO) {
        MovimientoResponseDTO nuevoMovimiento = movimientoService.createMovimiento(movimientoDTO);
        return new ResponseEntity<>(nuevoMovimiento, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un movimiento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> updateMovimiento(@PathVariable Long id, @Valid @RequestBody MovimientoDTO movimientoDTO) {
        MovimientoResponseDTO movimientoActualizado = movimientoService.updateMovimiento(id, movimientoDTO);
        return new ResponseEntity<>(movimientoActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todos los movimientos")
    @ApiResponse(responseCode = "200", description = "Listado de movimientos obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<MovimientoResponseDTO>> getAllMovimientos() {
        List<MovimientoResponseDTO> movimientos = movimientoService.getAllMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un movimiento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento encontrado"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> getMovimientoById(@PathVariable Long id) {
        MovimientoResponseDTO movimiento = movimientoService.getMovimientoById(id);
        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un movimiento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movimiento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
