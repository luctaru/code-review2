package br.edu.utfpr.apresentacao;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AulaController {
    
    @GetMapping("/aula")
    public String inicial(Model data) throws JsonSyntaxException, UnirestException {

        AulaModel arrayAulas[] = new Gson()
                    .fromJson(
                        Unirest
                            .get("http://localhost:8081/servico/aula")
                            .asJson()
                            .getBody()
                            .toString(), 
                        AulaModel[].class
                    );

        data.addAttribute("aulas", arrayAulas);

        return "aula-view";
    }

    @PostMapping ("/aula/criar")
    public String criar(AulaModel aula) throws UnirestException {

            Unirest.post("http://localhost:8081/servico/aula")
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(aula, AulaModel.class))
                .asJson();

        return "redirect:/aula";
    }

    @GetMapping ("/aula/excluir")
    public String excluir (@RequestParam int id) throws UnirestException {

        Unirest
            .delete("http://localhost:8081/servico/aula/{id}")
            .routeParam("id", String.valueOf(id))
            .asJson();

        return "redirect:/aula";
    }

    @GetMapping ("/aula/prepara-alterar")
    public String preparaAlterar (@RequestParam int id, Model data) throws JsonSyntaxException, UnirestException {

        AulaModel aulaExistente = new Gson()
            .fromJson(
                Unirest
                    .get("http://localhost:8081/servico/aula/{id}")
                    .routeParam("id", String.valueOf(id))
                    .asJson()
                    .getBody()
                    .toString(),
                AulaModel.class
            );

        data.addAttribute("aulaAtual", aulaExistente);

        AulaModel arrayAulas[] = new Gson()
        .fromJson(
            Unirest
                .get("http://localhost:8081/servico/aula")
                .asJson()
                .getBody()
                .toString(), 
            AulaModel[].class
        );

        data.addAttribute("aulas", arrayAulas);

        return "aula-view-alterar";
    }

    @GetMapping ("/aula/prepara-substituir")
    public String preparaSubstituir (@RequestParam int id, Model data) throws JsonSyntaxException, UnirestException {

        AulaModel aulaExistente = new Gson()
            .fromJson(
                Unirest
                    .get("http://localhost:8081/servico/aula/{id}")
                    .routeParam("id", String.valueOf(id))
                    .asJson()
                    .getBody()
                    .toString(),
                AulaModel.class
            );

        data.addAttribute("aulaAtual", aulaExistente);
        
        ProfessorModel arrayProfessores[] = new Gson()
        .fromJson(
            Unirest
                .get("http://localhost:8081/servico/professor")
                .asJson()
                .getBody()
                .toString(), 
            ProfessorModel[].class
        );
        
        data.addAttribute("professores", arrayProfessores);

        return "aula-view-substituir";
    }
    
    @PostMapping ("/aula/alterar")
    public String alterar (AulaModel aulaAlterada) throws UnirestException {

        Unirest
            .put("http://localhost:8081/servico/aula/{id}")
            .routeParam("id", String.valueOf(aulaAlterada.getId()))
            .header("Content-type", "application/json")
            .header("accept", "application/json")
            .body(new Gson().toJson(aulaAlterada, AulaModel.class))
            .asJson();

        return "redirect:/aula";
    }
}
