package cl.edubio360.importer.service;

import cl.edubio360.importer.dto.ImportacionRequest;
import cl.edubio360.importer.model.Importacion;
import cl.edubio360.importer.repository.ImportacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportServiceTest {
    @Mock ImportacionRepository repository;
    ImportService service;
    Importacion entity;

    @BeforeEach
    void setUp() {
        service = new ImportService(repository);
        entity = new Importacion("datos.csv", "VALIDADO");
    }

    @Test
    void crudCompleto() {
        when(repository.findAll()).thenReturn(List.of(entity));
        assertEquals(1, service.listar().size());

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        assertSame(entity, service.obtener(1L));

        when(repository.save(any(Importacion.class))).thenAnswer(i -> i.getArgument(0));
        ImportacionRequest request = new ImportacionRequest("datos.csv", "PROCESADO",
                List.of(new ImportacionRequest.ErrorRequest(2, "Fila inválida")));

        assertEquals("PROCESADO", service.crear(request).getEstado());
        assertEquals("PROCESADO", service.actualizar(1L, request).getEstado());

        service.eliminar(1L);
        verify(repository).delete(entity);
    }

    @Test
    void validaArchivo() {
        when(repository.save(any(Importacion.class))).thenAnswer(i -> i.getArgument(0));
        MockMultipartFile file = new MockMultipartFile("file", "datos.csv", "text/csv", "a,b".getBytes());
        assertEquals("VALIDADO", service.validarArchivo(file).getEstado());

        MockMultipartFile empty = new MockMultipartFile("file", "datos.csv", "text/csv", new byte[0]);
        assertThrows(ResponseStatusException.class, () -> service.validarArchivo(empty));

        MockMultipartFile invalid = new MockMultipartFile("file", "datos.txt", "text/plain", "x".getBytes());
        assertThrows(ResponseStatusException.class, () -> service.validarArchivo(invalid));
    }

    @Test
    void notFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.obtener(99L));
    }
}
