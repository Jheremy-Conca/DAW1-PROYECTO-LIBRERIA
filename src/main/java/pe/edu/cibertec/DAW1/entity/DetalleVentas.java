package pe.edu.cibertec.DAW1.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detalleventas")
public class DetalleVentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "IdVenta", nullable = false)
    private Ventas ventas;  // Esta es la propiedad que falta

    @ManyToOne
    @JoinColumn(name = "IdLibro", nullable = false)
    private Libros libro;

    @Column(nullable = false)
    private Integer Cantidad;

    @Column(nullable = false)
    private Double PrecioUnitario;

    private Double Descuento;

    @Column(nullable = false)
    private Double Subtotal;
}

