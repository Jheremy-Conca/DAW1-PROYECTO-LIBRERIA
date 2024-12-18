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
    private String imagenurl;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime FechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false) // nullable = false ensures a category is required
    private Categoria Categoria;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)

    private Proveedores Proveedor;


}


