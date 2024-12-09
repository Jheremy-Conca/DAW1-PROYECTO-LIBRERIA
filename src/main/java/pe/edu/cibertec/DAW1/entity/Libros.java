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
    private Integer IdLibro;

    @Column(nullable = false)
    private String Titulo;

    @Column(nullable = false)
    private String Autor;

    @Column(nullable = false)
    private String Genero;

    @Column(nullable = false)
    private Double Precio;

    @Enumerated(EnumType.STRING)
    private Estado Estado;

    private Integer AnoPublicacion;

    @Column(nullable = false)
    private Integer Stock;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime FechaRegistro;

    @ManyToOne
    @JoinColumn(name = "IdCategoria", nullable = false)
    private Categoria Categoria;

    @ManyToOne
    @JoinColumn(name = "IdProveedor", nullable = false)
    private Proveedores proveedor;
}

enum Estado {
    DISPONIBLE,
    AGOTADO,
    FUERA_DE_CATALOGO
}
