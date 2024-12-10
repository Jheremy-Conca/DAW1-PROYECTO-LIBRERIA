package pe.edu.cibertec.DAW1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer IdLibro;

    @Column(nullable = false)
    private String Titulo;

    @Column(nullable = false)
    private String Autor;

    @Column(nullable = false)
    private String Genero;

    @Column(nullable = false)
    private Double Precio;

    @Column(nullable = false)
    private String Estado;

    private Integer AnoPublicacion;

    @Column(nullable = false)
    private Integer Stock;

    @Column(nullable = false)
    private String ImagenURL;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime FechaRegistro;

    @ManyToOne
    @JoinColumn(name = "IdCategoria", nullable = false)
    private Categoria Categoria;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedores Proveedor;
    public String getImagenURL() {
        return ImagenURL;
    }

    public void setImagenURL(String imagenURL) {
        ImagenURL = imagenURL;
    }

    public Integer getIdLibro() {
        return IdLibro;
    }

    public void setIdLibro(Integer idLibro) {
        IdLibro = idLibro;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Integer getAnoPublicacion() {
        return AnoPublicacion;
    }

    public void setAnoPublicacion(Integer anoPublicacion) {
        AnoPublicacion = anoPublicacion;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }



    public LocalDateTime getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public pe.edu.cibertec.DAW1.entity.Categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(pe.edu.cibertec.DAW1.entity.Categoria categoria) {
        Categoria = categoria;
    }

    public Proveedores getProveedor() {
        return Proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        Proveedor = proveedor;
    }
}


