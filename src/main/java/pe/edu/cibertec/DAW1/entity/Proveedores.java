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

    public Integer getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        IdProveedor = idProveedor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }
}

