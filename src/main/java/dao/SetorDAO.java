package dao;

import model.SetorModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class SetorDAO implements CrudDAO<SetorModel> {
    private final Map<Integer, SetorModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<SetorModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<SetorModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(SetorModel setor) {
        int id = setor.getIdSetor() > 0 ? setor.getIdSetor() : nextId++;
        store.put(id, setor);
        return id;
    }

    @Override
    public void update(int id, SetorModel setor) {
        requireExists(id);
        store.put(id, setor);
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
            throw new NoSuchElementException("Setor não encontrado: " + id);
        }
    }
}

