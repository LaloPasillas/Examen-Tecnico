package com.example.articulos;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloRepository repo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("articulos", repo.findAll());
        return "articulos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("articulo", new Articulo());
        return "form-articulo";
    }

    @PostMapping("/guardar")
    public String guardar(
            @Valid Articulo articulo,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("articulo", articulo);
            return "form-articulo";
        }
        repo.save(articulo);
        return "redirect:/articulos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("articulo", repo.findById(id).orElse(null));
        return "form-articulo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/articulos";
    }

    @GetMapping("/reporte")
    public void generarReporte(HttpServletResponse response) throws Exception {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=articulos.pdf");

            List<Articulo> articulos = repo.findAll();

            InputStream reportStream = getClass().getResourceAsStream("/reports/articulos.jrxml");

            if (reportStream == null) {
                throw new RuntimeException("No se encontró el archivo JRXML en /reports/articulos.jrxml");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(articulos);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}