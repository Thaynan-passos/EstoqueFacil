package dao;

import model.EnderecoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class EnderecoDAO implements CrudDAO<EnderecoModel> {
    private final Map<Integer, EnderecoModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<EnderecoModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<EnderecoModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(EnderecoModel endereco) {
        int id = nextId++;
        store.put(id, endereco);
        return id;
    }

    @Override
    public void update(int id, EnderecoModel endereco) {
        requireExists(id);
        store.put(id, endereco);
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
            throw new NoSuchElementException("Endereco não encontrado: " + id);
        }
    }
}

