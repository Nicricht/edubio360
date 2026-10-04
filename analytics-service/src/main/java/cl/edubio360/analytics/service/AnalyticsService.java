package cl.edubio360.analytics.service;

import cl.edubio360.analytics.dto.MetricaRequest;
import cl.edubio360.analytics.model.Metrica;
import cl.edubio360.analytics.repository.MetricaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnalyticsService {
    private final MetricaRepository repository;

    public AnalyticsService(MetricaRepository repository) {
        this.repository = repository;
    }

    public List<Metrica> listar() { return repository.findAll(); }

    public Metrica obtener(Long id) { return get(id); }

    @Transactional
    public Metrica crear(MetricaRequest request) {
        Metrica entity = new Metrica(request.nombre(), request.descripcion());
        request.puntos().forEach(p -> entity.addPunto(p.etiqueta(), p.valor()));
        return repository.save(entity);
    }

    @Transactional
    public Metrica actualizar(Long id, MetricaRequest request) {
        Metrica entity = get(id);
        entity.actualizar(request.nombre(), request.descripcion());
        entity.getPuntos().clear();
        request.puntos().forEach(p -> entity.addPunto(p.etiqueta(), p.valor()));
        return entity;
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(get(id));
    }

    private Metrica get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Métrica no encontrada"));
    }
}
