package dao;

import model.FuncionarioModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class FuncionarioDAO implements CrudDAO<FuncionarioModel> {
    private final Map<Integer, FuncionarioModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<FuncionarioModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<FuncionarioModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(FuncionarioModel funcionario) {
        int id = funcionario.getIdFuncionario() > 0 ? funcionario.getIdFuncionario() : nextId++;
        store.put(id, funcionario);
        return id;
    }

    @Override
    public void update(int id, FuncionarioModel funcionario) {
        requireExists(id);
        store.put(id, funcionario);
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
            throw new NoSuchElementException("Funcionario não encontrado: " + id);
        }
    }
}

