/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import model.Producto;

/**
 *
 * @author admin
 */
public class ProductoDao {
    private List<Producto> lista;

    public ProductoDao() {
        lista = new ArrayList<>();
    }
    
    public void agregar(Producto p) {
        lista.add(p);
    }

    public List<Producto> getLista() {
        return lista;
    }
    
    public Map<String, List<Producto>> collectors_groupBy1() {
        return lista.stream().filter(p -> p.getPrecio()> 20).collect(Collectors.groupingBy(Producto::getNombre));
    }
    public Map<String, Long> collectors_groupBy2() {
        return lista.stream().collect(Collectors.groupingBy(Producto::getNombre, Collectors.counting()));
    }
    public Map<String, Double> collectors_groupBy3() {
        return lista.stream()
                .collect(Collectors.groupingBy(Producto::getNombre, 
                        Collectors.summingDouble(Producto::getPrecio)));
    }
    public DoubleSummaryStatistics suma_resumen() {
        return lista.stream().collect(Collectors.summarizingDouble(Producto::getPrecio));
    }
    public Optional<Double> reduce() {
        return lista.stream().map(Producto::getPrecio).reduce(Double::sum);
    }
}
