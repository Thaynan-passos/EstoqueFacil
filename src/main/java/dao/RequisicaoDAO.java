package dao;

import model.RequisicaoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class RequisicaoDAO implements CrudDAO<RequisicaoModel> {
    private final Map<Integer, RequisicaoModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<RequisicaoModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<RequisicaoModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(RequisicaoModel requisicao) {
        int id = requisicao.getIdRequisicao() > 0 ? requisicao.getIdRequisicao() : nextId++;
        store.put(id, requisicao);
        return id;
    }

    @Override
    public void update(int id, RequisicaoModel requisicao) {
        requireExists(id);
        store.put(id, requisicao);
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
            throw new NoSuchElementException("Requisição não encontrada: " + id);
        }
    }
}

