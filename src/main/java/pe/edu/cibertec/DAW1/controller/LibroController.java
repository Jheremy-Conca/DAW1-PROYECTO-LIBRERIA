package pe.edu.cibertec.DAW1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.DAW1.dto.LibroDto.LibroDetailDto;
import pe.edu.cibertec.DAW1.entity.Libros;
import pe.edu.cibertec.DAW1.service.IUploadFileService;
import pe.edu.cibertec.DAW1.service.LibroService;

import java.net.MalformedURLException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping
    public String listLibros(@RequestParam(required = false) String nombre, Model model)  throws Exception{
        try {
            if (nombre != null && !nombre.isEmpty()) {
                model.addAttribute("listlibro", libroService.buscarPorTituloOAutor(nombre));
                model.addAttribute("nombreBusqueda", nombre);  // Guardar el término de búsqueda
            } else {
                model.addAttribute("listlibro", libroService.listAllLibros());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "libro";
    }

    @GetMapping(value = "/uploads/{filename}")
    public ResponseEntity<Resource> goImage(@PathVariable String filename)  throws Exception{
        Resource resource = null;
        try {
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/new")
    public String newLibro(Model model) throws Exception {
        model.addAttribute("libro", new Libros());
        model.addAttribute("listlibro", libroService.listAllLibros());
        return "libro-create";
    }

    @PostMapping("/save")
    public String saveLibro(@Validated @ModelAttribute("libro") Libros libro, BindingResult result, Model model,
                            @RequestParam("file") MultipartFile image, RedirectAttributes flash, SessionStatus status)
            throws Exception {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError());
            return "libro-create";
        } else {
            // Asignar la fecha y hora actual a FechaRegistro
            if (libro.getFechaRegistro() == null) {
                libro.setFechaRegistro(LocalDateTime.now());
            }

            if (!image.isEmpty()) {
                if (libro.getImagenurl() != null && libro.getImagenurl().length() > 0) {
                    uploadFileService.delete(libro.getImagenurl());
                }
                String uniqueFileName = uploadFileService.copy(image);
                libro.setImagenurl(uniqueFileName);
            }
            libroService.saveLibro(libro);
            status.setComplete();
        }
        return "redirect:/libro";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Integer id, Model model) throws Exception{
        LibroDetailDto libroEditDto = libroService.getLibroById(id);
        model.addAttribute("libro", libroEditDto);
        return "libro-edit";
    }

    @PostMapping("/update-confirm")
    public String edit(@Validated @ModelAttribute Libros libros, BindingResult result,
                       @RequestParam("file") MultipartFile image, RedirectAttributes flash, SessionStatus status) throws Exception {
        if (result.hasErrors()) {
            // Handle validation errors (e.g., empty fields)
            return "libro-edit";
        }

        // Handle file upload if a new image is provided
        if (!image.isEmpty()) {
            if (libros.getImagenurl() != null && libros.getImagenurl().length() > 0) {
                // Delete old image if new one is uploaded
                uploadFileService.delete(libros.getImagenurl());
            }
            String uniqueFileName = uploadFileService.copy(image);
            libros.setImagenurl(uniqueFileName); // Set the new image URL
        }

        // Call the service to update the book record
        libroService.updateLibro(libros);

        // Redirect after successful update
        status.setComplete();
        flash.addFlashAttribute("message", "Libro actualizado correctamente.");
        return "redirect:/libro";
    }



    @RequestMapping("/detail/{id}")
    public String goDetail(@PathVariable(value = "id") int id, Model model)  throws Exception{
        Libros libro = libroService.listById(id);
        model.addAttribute("libro", libro);
        return "libro-detail";
    }

    @RequestMapping("/delete/{id}")
    public String eliminar(@PathVariable(value = "id") int id)  throws Exception{
        try {
            libroService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/libro";
    }
}
