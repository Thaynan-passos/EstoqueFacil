package dao;

import model.TelefoneModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class TelefoneDAO implements CrudDAO<TelefoneModel> {
    private final Map<Integer, TelefoneModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<TelefoneModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<TelefoneModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(TelefoneModel telefone) {
        int id = nextId++;
        store.put(id, telefone);
        return id;
    }

    @Override
    public void update(int id, TelefoneModel telefone) {
        requireExists(id);
        store.put(id, telefone);
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
            throw new NoSuchElementException("Telefone não encontrado: " + id);
        }
    }
}

