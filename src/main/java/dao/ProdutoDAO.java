package dao;

import model.ProdutoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class ProdutoDAO implements CrudDAO<ProdutoModel> {
    private final Map<Integer, ProdutoModel> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public List<ProdutoModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<ProdutoModel> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int save(ProdutoModel produto) {
        int id = produto.getIdProduto() > 0 ? produto.getIdProduto() : nextId++;
        store.put(id, produto);
        return id;
    }

    @Override
    public void update(int id, ProdutoModel produto) {
        requireExists(id);
        store.put(id, produto);
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
            throw new NoSuchElementException("Produto não encontrado: " + id);
        }
    }
}

