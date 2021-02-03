package br.com.zup.mercadolivre.produto;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadFicticio {

    public List<String> get(List<MultipartFile> imagens ){

        return  imagens.stream().map(imagemVirtual->"http://bucket/"+imagemVirtual.getOriginalFilename()).collect(Collectors.toList());

    }

}
