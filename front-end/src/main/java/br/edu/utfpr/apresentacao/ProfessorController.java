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
public class ProfessorController {
    
    @GetMapping("/professor")
    public String inicial(Model data) throws JsonSyntaxException, UnirestException {

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

        return "professor-view";
    }

    @PostMapping ("/professor/criar")
    public String criar(ProfessorModel professor) throws UnirestException {

            Unirest.post("http://localhost:8081/servico/professor")
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(professor, ProfessorModel.class))
                .asJson();

        return "redirect:/professor";
    }

    @GetMapping ("/professor/excluir")
    public String excluir (@RequestParam int ra) throws UnirestException {

        Unirest
            .delete("http://localhost:8081/servico/professor/{ra}")
            .routeParam("ra", String.valueOf(ra))
            .asJson();

        return "redirect:/professor";
    }

    @GetMapping ("/professor/prepara-alterar")
    public String preparaAlterar (@RequestParam int ra, Model data) throws JsonSyntaxException, UnirestException {

        ProfessorModel professorExistente = new Gson()
            .fromJson(
                Unirest
                    .get("http://localhost:8081/servico/professor/{ra}")
                    .routeParam("ra", String.valueOf(ra))
                    .asJson()
                    .getBody()
                    .toString(),
                ProfessorModel.class
            );

        data.addAttribute("professorAtual", professorExistente);

        ProfessorModel arrayProfessores[] = new Gson()
        .fromJson(
            Unirest
                .get("http://localhost:8081/servico/professor")
                .asJson()
                .getBody()
                .toString(), 
            ProfessorModel[].class
        );

        data.addAttribute("paises", arrayProfessores);

        return "professor-view-alterar";
    }

    @PostMapping ("/professor/alterar")
    public String alterar (ProfessorModel professorAlterado) throws UnirestException {

        Unirest
            .put("http://localhost:8081/servico/professor/{ra}")
            .routeParam("ra", String.valueOf(professorAlterado.getRa()))
            .header("Content-type", "application/json")
            .header("accept", "application/json")
            .body(new Gson().toJson(professorAlterado, ProfessorModel.class))
            .asJson();

        return "redirect:/professor";
    }
}
