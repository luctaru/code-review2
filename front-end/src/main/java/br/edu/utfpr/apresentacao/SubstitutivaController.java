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
public class SubstitutivaController {
    
    @GetMapping("/substitutiva")
    public String inicial(Model data) throws JsonSyntaxException, UnirestException {

        SubstitutivaModel arraySubstitutivas[] = new Gson()
                    .fromJson(
                        Unirest
                            .get("http://localhost:8081/servico/substitutiva")
                            .asJson()
                            .getBody()
                            .toString(), 
                        SubstitutivaModel[].class
                    );

        data.addAttribute("substitutivas", arraySubstitutivas);

        return "substitutiva-view";
    }

    @PostMapping ("/substitutiva/criar")
    public String criar(SubstitutivaModel substitutiva) throws UnirestException {

            Unirest.post("http://localhost:8081/servico/substitutiva")
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(substitutiva, SubstitutivaModel.class))
                .asJson();

        return "redirect:/substitutiva";
    }

    @GetMapping ("/substitutiva/excluir")
    public String excluir (@RequestParam int id) throws UnirestException {

        Unirest
            .delete("http://localhost:8081/servico/substitutiva/{id}")
            .routeParam("id", String.valueOf(id))
            .asJson();

        return "redirect:/substitutiva";
    }

    @GetMapping ("/substitutiva/prepara-alterar")
    public String preparaAlterar (@RequestParam int id, Model data) throws JsonSyntaxException, UnirestException {

        SubstitutivaModel substitutivaExistente = new Gson()
            .fromJson(
                Unirest
                    .get("http://localhost:8081/servico/substitutiva/{id}")
                    .routeParam("id", String.valueOf(id))
                    .asJson()
                    .getBody()
                    .toString(),
                SubstitutivaModel.class
            );

        data.addAttribute("substitutivaAtual", substitutivaExistente);

        SubstitutivaModel arraySubstitutivas[] = new Gson()
        .fromJson(
            Unirest
                .get("http://localhost:8081/servico/substitutiva")
                .asJson()
                .getBody()
                .toString(), 
            SubstitutivaModel[].class
        );

        data.addAttribute("substitutivas", arraySubstitutivas);

        return "substitutiva-view-alterar";
    }

    @PostMapping ("/substitutiva/alterar")
    public String alterar (SubstitutivaModel substitutivaAlterada) throws UnirestException {

        Unirest
            .put("http://localhost:8081/servico/substitutiva/{id}")
            .routeParam("id", String.valueOf(substitutivaAlterada.getId()))
            .header("Content-type", "application/json")
            .header("accept", "application/json")
            .body(new Gson().toJson(substitutivaAlterada, SubstitutivaModel.class))
            .asJson();

        return "redirect:/substitutiva";
    }
}
