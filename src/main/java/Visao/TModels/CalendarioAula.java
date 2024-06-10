
package Visao.TModels;

public class CalendarioAula {
    private int id;
    private int idMateria;
    private String dataAula;
    private String horaInicio;
    private String horaFim;

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdMateria() {
        return idMateria;
    }
    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }
    public String getDataAula() {
        return dataAula;
    }
    public void setDataAula(String dataAula) {
        this.dataAula = dataAula;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    public String getHoraFim() {
        return horaFim;
    }
    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }
}
