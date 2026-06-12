package dao;

import model.RelatorioModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class RelatorioDAO implements CrudDAO<RelatorioModel> {
    private final Map<Integer, RelatorioModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<RelatorioModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<RelatorioModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(RelatorioModel relatorio) {
        int id = relatorio.getIdRelatorio() > 0 ? relatorio.getIdRelatorio() : nextId++;
        store.put(id, relatorio);
        return id;
    }

    @Override
    public void update(int id, RelatorioModel relatorio) {
        requireExists(id);
        store.put(id, relatorio);
    }

    @Override
    public void delete(int id) {
        store.remove(id);
    }

    @Override
    public boolean existsById(int id) {
        return store.containsKey(id);
    }

    private void requireExists(int id) {
        if (!store.containsKey(id)) {
            throw new NoSuchElementException("Relatório não encontrado: " + id);
        }
    }
}

