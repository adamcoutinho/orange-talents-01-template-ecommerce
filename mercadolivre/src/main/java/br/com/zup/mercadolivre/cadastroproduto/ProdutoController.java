package br.com.zup.mercadolivre.cadastroproduto;

import br.com.zup.mercadolivre.cadastrousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private VerificaQuantidadeCaracteristicas verificaQuantidadeCaracteristicas;

    @Autowired
    private UploadFicticio uploadFicticio;

    @InitBinder
    public void InitBinder(WebDataBinder binder){
        binder.addValidators(this.verificaQuantidadeCaracteristicas);
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoFormRequest request) {
        Produto produto = request.toModel(manager);
        manager.persist(produto);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }


    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?>  adicionarImagens(@PathVariable("id") @NotNull(message = "id do produto não pode ser nulo.") Long id , ImagemFormRequest request) {

        Usuario usuario = (Usuario) this.manager
                .createQuery("SELECT u FROM Usuario u WHERE u.login like :email").setParameter("email","admin@gmail.com")
                .getSingleResult();

        Produto produto = manager
                .find(Produto.class,id);

        produto.adicionaImagens(uploadFicticio.get(request.getImagens()));
        manager.merge(produto);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }
}
