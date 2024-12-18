package pe.edu.cibertec.DAW1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDetailDto;
import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDetailDto;
import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDto;
import pe.edu.cibertec.DAW1.entity.Categoria;
import pe.edu.cibertec.DAW1.entity.Proveedores;
import pe.edu.cibertec.DAW1.repository.ProveedorRepository;
import pe.edu.cibertec.DAW1.service.ProveedorService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorDto> getAllProveedor() throws Exception{
        List<ProveedorDto> proveedoresDto = new ArrayList<>();
        Iterable<Proveedores> iterable = proveedorRepository.findAll();
        iterable.forEach(proveedores -> {
            ProveedorDto proveedorDto = new ProveedorDto(
                    proveedores.getIdProveedor(),
                    proveedores.getNombre(),
                    proveedores.getTelefono(),
                    proveedores.getEmail()
            );
            proveedoresDto.add(proveedorDto);
        });
        return proveedoresDto;
    }

    @Override
    public Optional<ProveedorDetailDto> getDetailProveedor(int id) throws Exception {
        Optional<Proveedores> optional =proveedorRepository.findById(id);
        return optional.map(proveedor -> new ProveedorDetailDto(proveedor.getIdProveedor(),
                proveedor.getNombre(),
                proveedor.getTelefono(),
                proveedor.getEmail(),
                proveedor.getDireccion(),
                proveedor.getFechaRegistro()));
    }
    @Override
    public ProveedorDetailDto getProveedorById(int id) throws Exception{
        Optional<Proveedores> optional = proveedorRepository.findById(id);
        return optional.map(proveedores -> new ProveedorDetailDto(
                        proveedores.getIdProveedor(),
                        proveedores.getNombre(),
                        proveedores.getTelefono(),
                        proveedores.getEmail(),
                        proveedores.getDireccion(),
                        proveedores.getFechaRegistro()))
                .orElse(null);
    }

    @Override
    public Boolean updateProveedor(ProveedorDetailDto proveedorDetailDto) throws Exception{
        Optional<Proveedores> optional = proveedorRepository.findById(proveedorDetailDto.IdProveedor());
        return optional.map(proveedores -> {
            proveedores.setNombre(proveedorDetailDto.Nombre());
            proveedores.setTelefono(proveedorDetailDto.Telefono());
            proveedores.setEmail(proveedorDetailDto.Email());
            proveedores.setDireccion(proveedorDetailDto.Direccion());

            // Only set the FechaRegistro if provided or use the current time
            if (proveedorDetailDto.FechaRegistro() == null) {
                proveedores.setFechaRegistro(LocalDateTime.now());
            } else {
                proveedores.setFechaRegistro(proveedorDetailDto.FechaRegistro());
            }

            proveedorRepository.save(proveedores);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean deleteProveedor(int id) {
        Optional<Proveedores> optional = proveedorRepository.findById(id);
        return optional.map(proveedores -> {
            proveedorRepository.delete(proveedores);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean createProveedor(ProveedorDetailDto proveedorDetailDto) {
        Proveedores proveedores = new Proveedores();
        proveedores.setNombre(proveedorDetailDto.Nombre());
        proveedores.setTelefono(proveedorDetailDto.Telefono());
        proveedores.setEmail(proveedorDetailDto.Email());
        proveedores.setDireccion(proveedorDetailDto.Direccion());

        // If FechaRegistro is null, set it to the current time
        if (proveedorDetailDto.FechaRegistro() == null) {
            proveedores.setFechaRegistro(LocalDateTime.now());
        } else {
            proveedores.setFechaRegistro(proveedorDetailDto.FechaRegistro());
        }

        Proveedores savedProveedor = proveedorRepository.save(proveedores);


        return savedProveedor != null;  // Retorna true si la categoría fue guardada correctamente
    }

    @Override
    public List<ProveedorDto> searchProveedoresByNombre(String nombre) {
        List<Proveedores> proveedores = proveedorRepository.findByNombreProveedorContaining(nombre);
        List<ProveedorDto> proveedorDto = new ArrayList<>();

        for (Proveedores proveedor : proveedores) {
            proveedorDto.add(new ProveedorDto(
                    proveedor.getIdProveedor(),
                    proveedor.getNombre(),
                    proveedor.getTelefono(),
                    proveedor.getEmail()
            ));
        }

        return proveedorDto;
    }
}
