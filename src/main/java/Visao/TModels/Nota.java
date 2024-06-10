package Visao.TModels;


public class Nota {

    private int id;
    private int idAluno;
    private int idMateria;
    private int semestre;
    private double nota;

    // Getters e Setters
    public Nota() {
    }

    public Nota(int idAluno, int idMateria, int semestre, double nota) {
        this.idAluno = idAluno;
        this.idMateria = idMateria;
        this.semestre = semestre;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
