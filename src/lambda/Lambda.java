
package lambda;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.Persona;
import model.Producto;

/**
 *
 * @author admin
 */
public class Lambda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Persona p1 = new Persona(1, "Alex", LocalDate.of(1992, 1, 25));
        Persona p2 = new Persona(2, "Ana", LocalDate.of(1993, 3, 21));
        Persona p3 = new Persona(3, "Maria", LocalDate.of(1983, 7, 23));
        Persona p4 = new Persona(4, "Ruth", LocalDate.of(2018, 2, 15));
        Persona p5 = new Persona(5, "Carlos", LocalDate.of(2011, 7, 4));

        Producto pr1 = new Producto(1, "Laptop", 3000.0);
        Producto pr2 = new Producto(2, "Teclado", 300.50);
        Producto pr3 = new Producto(3, "Pantalla", 1000.0);
        Producto pr4 = new Producto(4, "Mouse", 150.0);

        List<Persona> personas = Arrays.asList(p1, p2, p3, p4, p5);
        List<Producto> productos = Arrays.asList(pr1, pr2, pr3, pr4);
        
       // personas.forEach(System.out::println);
       // productos.forEach(System.out::println);
       
       //Filter (param: Predicate) Listar los mayores a 20 años
        List<Persona> filterList1 = personas.stream()
                .filter(p->Lambda.getAge(p.getFechaNac())>=20)
                .collect(Collectors.toList());
        Lambda.printList(filterList1);
        
        
    }
    public static int getAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static void printList(List<?> list){
        list.forEach(System.out::println);
    }
}
