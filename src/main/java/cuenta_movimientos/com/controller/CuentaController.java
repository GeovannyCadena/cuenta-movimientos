package cuenta_movimientos.com.controller;

import cuenta_movimientos.com.dto.request.CuentaDTO;
import cuenta_movimientos.com.dto.response.CuentaResponseDTO;
import cuenta_movimientos.com.service.interfaces.CuentaService;
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
@RequestMapping("/api/cuentas")
@Tag(name = "Cuentas", description = "Operaciones relacionadas con las cuentas bancarias")
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @Operation(summary = "Crear una nueva cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos enviados")
    })
    @PostMapping
    public ResponseEntity<CuentaResponseDTO> createCuenta(@Valid @RequestBody CuentaDTO cuentaDTO) {
        CuentaResponseDTO nuevaCuenta = cuentaService.saveCuenta(cuentaDTO);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una cuenta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> updateCuenta(@PathVariable Long id, @Valid @RequestBody CuentaDTO cuentaDTO) {
        CuentaResponseDTO cuentaActualizada = cuentaService.updateCuenta(id, cuentaDTO);
        return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las cuentas")
    @ApiResponse(responseCode = "200", description = "Listado de cuentas obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<CuentaResponseDTO>> getAllCuentas() {
        List<CuentaResponseDTO> cuentas = cuentaService.getAllCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @Operation(summary = "Obtener una cuenta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta encontrada"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> getCuentaById(@PathVariable Long id) {
        CuentaResponseDTO cuenta = cuentaService.getCuentaById(id);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una cuenta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cuenta eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
