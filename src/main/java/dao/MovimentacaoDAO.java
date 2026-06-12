package dao;

import model.MovimentacaoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class MovimentacaoDAO implements CrudDAO<MovimentacaoModel> {
    private final Map<Integer, MovimentacaoModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<MovimentacaoModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<MovimentacaoModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(MovimentacaoModel movimentacao) {
        int id = movimentacao.getIdMovimentacao() > 0 ? movimentacao.getIdMovimentacao() : nextId++;
        store.put(id, movimentacao);
        return id;
    }

    @Override
    public void update(int id, MovimentacaoModel movimentacao) {
        requireExists(id);
        store.put(id, movimentacao);
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
            throw new NoSuchElementException("Movimentação não encontrada: " + id);
        }
    }
}

