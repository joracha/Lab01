package com.example.lenovo.lab.AccesoDatos;

import com.example.lenovo.lab.LogicaNeg.Alumno;
import com.example.lenovo.lab.LogicaNeg.Carrera;
import com.example.lenovo.lab.LogicaNeg.Ciclo;
import com.example.lenovo.lab.LogicaNeg.Curso;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.LogicaNeg.Profesor;
import com.example.lenovo.lab.LogicaNeg.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/03/2018.
 */

public class ModelData {

    private List<Curso> cursoList;
    private List<Profesor> profesorList;
    private List<Carrera> carreraList;

    public ModelData() {
        cursoList = new ArrayList<>();
        profesorList = new ArrayList<>();
        carreraList = new ArrayList<>();
        prepareCursoData();
        prepareProfesorData();
    }

    public void prepareCarreraData(){

        Carrera carrera = new Carrera(1, "EIF", "Ingenieria En Sistemas");
        carreraList.add(carrera);

        carrera = new Carrera(2, "ADM", "Administracion de Empresas");
        carreraList.add(carrera);

        carrera = new Carrera(3, "MAT", "Matematicas");
        carreraList.add(carrera);

        carrera = new Carrera(4, "LIX", "Ingles");
        carreraList.add(carrera);

        carrera = new Carrera(5, "EDU", "Educacion");
        carreraList.add(carrera);

    }


    public void prepareCursoData() {
        Curso curso = new Curso("ST", "Soporte", 3, 4);
        cursoList.add(curso);
        curso = new Curso("FD", "Fundamentos", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PG1", "Programacion I", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PG2", "Programacion II", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PG3", "Programacion III", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PG4", "Programacion IV", 3, 4);
        cursoList.add(curso);
        curso = new Curso("EDA", "Estructuras Datos", 3, 4);
        cursoList.add(curso);
        curso = new Curso("EDI", "Estructuras Discretas", 3, 4);
        cursoList.add(curso);

        curso = new Curso("MV", "Moviles", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PP", "Paradigmas", 3, 4);
        cursoList.add(curso);

        curso = new Curso("AQ", "Arquitectura", 3, 4);
        cursoList.add(curso);

        curso = new Curso("RD", "Redes", 3, 4);
        cursoList.add(curso);
        // de adm
        curso = new Curso("FAD", "Fundamentos de Administracion", 3, 4);
        cursoList.add(curso);
        curso = new Curso("C1", "Contabilidad I", 3, 4);
        cursoList.add(curso);
        // de fisica
        curso = new Curso("FF", "Fundamentos de Fisica", 3, 4);
        cursoList.add(curso);
        curso = new Curso("F1", "Fisica I", 3, 4);
        cursoList.add(curso);
        // de matematica
        curso = new Curso("FM", "Fundamentos de Matematica", 3, 4);
        cursoList.add(curso);
        curso = new Curso("HB1", "Historia Basica I", 3, 4);
        cursoList.add(curso);
        // de science fiction
        curso = new Curso("M1", "Matrix 1", 3, 4);
        cursoList.add(curso);
        curso = new Curso("BDR", "Blade Runner 2049", 3, 4);
        cursoList.add(curso);
        // de animation
        curso = new Curso("ZOO", "Zootopia", 3, 4);
        cursoList.add(curso);
        curso = new Curso("VIN", "Vecinos Invasores", 3, 4);
        cursoList.add(curso);
        // de classics
        curso = new Curso("TIT", "Titanic", 3, 4);
        cursoList.add(curso);
        curso = new Curso("DDA", "Donnie Darko", 3, 4);
        cursoList.add(curso);
        // de commedy
        curso = new Curso("WDG", "War Dogs", 3, 4);
        cursoList.add(curso);
        curso = new Curso("ANT", "Ant Man", 3, 4);
        cursoList.add(curso);
        // de Science Fiction & Fantasy
        curso = new Curso("JL", "Justice League", 3, 4);
        cursoList.add(curso);
        curso = new Curso("AVG", "Avengers", 3, 4);
        cursoList.add(curso);
    }


    public void prepareProfesorData() {
        Profesor profesor = new Profesor("123", "Jose", "@jose", 678);
        profesorList.add(profesor);

        profesor = new Profesor("234", "Juan", "@juan", 876);
        profesorList.add(profesor);

        profesor = new Profesor("345", "Mario", "@mario", 789);
        profesorList.add(profesor);

        profesor = new Profesor("456", "Jesus", "@Jesus", 978);
        profesorList.add(profesor);
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }


    public List<Usuario> getUsuariosList() {
        List<Usuario> users = new ArrayList<>();
        users.add(new Usuario("@admin", "admin", "administrador", "111"));
        users.add(new Usuario("@admin2", "admin", "administrador", "222"));
        users.add(new Usuario("@matric", "matric", "matriculador", "333"));
        users.add(new Usuario("@matric1", "matric", "matriculador", "444"));
        users.add(new Usuario("@matric2", "matric", "matriculador", "555"));
        users.add(new Usuario("@matric3", "matric", "matriculador", "555"));
        return users;
    }
}
