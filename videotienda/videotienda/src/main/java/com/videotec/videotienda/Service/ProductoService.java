package com.videotec.videotienda.Service;

import com.videotec.videotienda.Models.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> listarTodos();
    Producto guardar(Producto producto);
    Producto obtenerPorId(Long id);
    void eliminar(Long id);
    Producto actualizarProducto(Long id, Producto productoActualizado);

}

