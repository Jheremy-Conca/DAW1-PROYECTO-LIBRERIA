package pe.edu.cibertec.DAW1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdVenta;

    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private Usuarios Usuario;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime FechaVenta;

    @Column(nullable = false)
    private Double TotalVenta;

    @OneToMany(mappedBy = "ventas")
    private List<DetalleVentas> detalleVentas;
}

