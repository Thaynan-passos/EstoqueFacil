package dao;

import model.FornecedorModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class FornecedorDAO implements CrudDAO<FornecedorModel> {
    private final Map<Integer, FornecedorModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<FornecedorModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<FornecedorModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(FornecedorModel fornecedor) {
        int id = fornecedor.getIdFornecedor() > 0 ? fornecedor.getIdFornecedor() : nextId++;
        store.put(id, fornecedor);
        return id;
    }

    @Override
    public void update(int id, FornecedorModel fornecedor) {
        requireExists(id);
        store.put(id, fornecedor);
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
            throw new NoSuchElementException("Fornecedor não encontrado: " + id);
        }
    }
}

