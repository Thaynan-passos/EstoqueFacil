package dao;

import model.LoteModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class LoteDAO implements CrudDAO<LoteModel> {
    private final Map<Integer, LoteModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<LoteModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<LoteModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(LoteModel lote) {
        int id = lote.getIdLote() > 0 ? lote.getIdLote() : nextId++;
        store.put(id, lote);
        return id;
    }

    @Override
    public void update(int id, LoteModel lote) {
        requireExists(id);
        store.put(id, lote);
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
            throw new NoSuchElementException("Lote não encontrado: " + id);
        }
    }
}

