package cuenta_movimientos.com.controller;

import cuenta_movimientos.com.dto.response.ReporteResponseDTO;
import cuenta_movimientos.com.service.impl.ReporteServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@Tag(name = "Reportes", description = "Operaciones relacionadas con reportes de movimientos")
public class ReporteController {

    @Autowired
    private ReporteServiceImp reporteService;

    @Operation(
            summary = "Obtener reporte por fechas",
            description = "Devuelve una lista de movimientos por cuenta entre la fecha de inicio y la fecha fin",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de reportes generada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReporteResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @GetMapping("/porfecha")
    public List<ReporteResponseDTO> obtenerReportePorFecha(@RequestParam("fechaInicio") String fechaInicio,
                                                           @RequestParam("fechaFin") String fechaFin) {
        return reporteService.obtenerReportePorFecha(fechaInicio, fechaFin);
    }
}
