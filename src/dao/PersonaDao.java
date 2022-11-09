/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import model.Persona;

/**
 *
 * @author admin
 */
public class PersonaDao {

    private List<Persona> lista;

    public PersonaDao() {
        lista = new ArrayList<>();
    }

    public List<Persona> getLista() {
        return lista;
    }

    public void agregar(Persona p) {
        lista.add(p);
    }

    public List<Persona> edadMayor() {
        List<Persona> filterList1 = lista.stream()
                .filter(p -> getAge(p.getFechaNac()) >= 20)
                .collect(Collectors.toList());
        return filterList1;
    }

    public List<Persona> compareto() {
        Comparator<Persona> byNameAsc = (Persona o1, Persona o2) -> o1.getNombre().compareTo(o2.getNombre());
        Comparator<Persona> byNameDesc = (Persona o1, Persona o2) -> o2.getNombre().compareTo(o1.getNombre());
        Comparator<Persona> byBirthDate = (Persona o1, Persona o2) -> o1.getFechaNac().compareTo(o2.getFechaNac());

        return lista.stream().sorted(byBirthDate).collect(Collectors.toList());
    }
    public boolean match() {
        Predicate<Persona> startsWithPredicate = person -> person.getNombre().startsWith("J");
        return lista.stream().allMatch(startsWithPredicate); 
    }
    public List<String> map() {
        Function<String, String> coderFunction = name -> "Coder " + name;
        return lista.stream().map(Persona::getNombre).map(coderFunction).collect(Collectors.toList());
    }
    public List<Persona> limit() {
        int pageNumber = 1;
        int pageSize = 2;
        return lista.stream().skip(pageNumber * pageSize).limit(pageSize).collect(Collectors.toList());
    }
    public int getAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public void printList(List<?> list) {
        list.forEach(System.out::println);
    }
}
