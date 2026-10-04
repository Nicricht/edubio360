package cl.edubio360.importer.service;

import cl.edubio360.importer.dto.ImportacionRequest;
import cl.edubio360.importer.model.Importacion;
import cl.edubio360.importer.repository.ImportacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@Service
public class ImportService {
    private final ImportacionRepository repository;

    public ImportService(ImportacionRepository repository) {
        this.repository = repository;
    }

    public List<Importacion> listar() { return repository.findAll(); }

    public Importacion obtener(Long id) { return get(id); }

    @Transactional
    public Importacion crear(ImportacionRequest request) {
        Importacion entity = new Importacion(request.archivo(), request.estado());
        request.errores().forEach(e -> entity.addError(e.fila(), e.mensaje()));
        return repository.save(entity);
    }

    @Transactional
    public Importacion actualizar(Long id, ImportacionRequest request) {
        Importacion entity = get(id);
        entity.actualizar(request.archivo(), request.estado());
        entity.getErrores().clear();
        request.errores().forEach(e -> entity.addError(e.fila(), e.mensaje()));
        return entity;
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(get(id));
    }

    @Transactional
    public Importacion validarArchivo(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El archivo está vacío");
        }
        String filename = file.getOriginalFilename() == null ? "archivo" : file.getOriginalFilename();
        String lower = filename.toLowerCase(Locale.ROOT);
        if (!lower.endsWith(".xlsx") && !lower.endsWith(".csv")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato no soportado. Use .xlsx o .csv");
        }
        return repository.save(new Importacion(filename, "VALIDADO"));
    }

    private Importacion get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Importación no encontrada"));
    }
}
