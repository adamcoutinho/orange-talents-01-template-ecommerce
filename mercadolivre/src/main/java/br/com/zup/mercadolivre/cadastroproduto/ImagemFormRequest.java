package br.com.zup.mercadolivre.cadastroproduto;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ImagemFormRequest {
    @NotNull(message = "imagem não pode ser nula.")
    @Size(max = 1,message = "adicione pelo menos 1 imagem.")
    private List<MultipartFile> imagens;
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ImagemFormRequest(@NotNull(message = "imagem não pode ser nula.") @Size(max = 1, message = "adicione pelo menos 1 imagem.") List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }
}
