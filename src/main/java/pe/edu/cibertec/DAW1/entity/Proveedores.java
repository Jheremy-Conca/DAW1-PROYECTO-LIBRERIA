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
public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdProveedor;

    @Column(nullable = false)
    private String Nombre;

    private String Telefono;

    private String Email;

    @Column(columnDefinition = "TEXT")
    private String Direccion;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime FechaRegistro;


}

