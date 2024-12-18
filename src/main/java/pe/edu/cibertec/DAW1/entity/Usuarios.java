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
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdUsuario;

    @Column(unique = true, nullable = false)
    private String NombreUsuario;

    @Column(nullable = false)
    private String Contraseña;

    @Column(unique = true, nullable = false)
    private String Email;

    @Enumerated(EnumType.STRING)
    private Rol Rol;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime FechaRegistro;
}

enum Rol {
    ADMINISTRADOR,
    CLIENTE
}
