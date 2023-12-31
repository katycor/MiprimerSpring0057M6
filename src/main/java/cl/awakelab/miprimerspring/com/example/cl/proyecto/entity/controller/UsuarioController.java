package cl.awakelab.miprimerspring.com.example.cl.proyecto.entity.controller;


import cl.awakelab.miprimerspring.com.example.cl.proyecto.entity.entity.Usuario;
import cl.awakelab.miprimerspring.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    IUsuarioService objUsuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> listaUsuarios = objUsuarioService.listarUsuarios();
        model.addAttribute("atributoListaUsuarios", listaUsuarios);
        return "templateListarUsuarios";
    }

    @GetMapping("/crearUsuario")
    public String mostrarFormularioCrearUsuario() {
        return "templateFormularioCrearUsuario";
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(@ModelAttribute Usuario usuario) {
        objUsuarioService.crearUsuario(usuario);
        return "redirect:/usuario";

    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        objUsuarioService.eliminarUsuario(id);
        return "redirect:/usuario";
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable int id, Model model) {
        Usuario usuario = objUsuarioService.listarUsuarioId(id);
        if (usuario == null) {
            return "redirect:/usuario";
        }
        model.addAttribute("usuario", usuario);
        return "templateFormularioEditarUsuario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarUsuario(@PathVariable int id, @ModelAttribute Usuario usuario) {
        // Verificamos si el usuario existe antes de actualizar
        objUsuarioService.actualizarUsuario(usuario);
        return "redirect:/usuario";
    }
}






















