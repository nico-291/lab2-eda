import java.util.LinkedList;
import java.util.Queue;

public class Votaciones{
    public class Voto{
    private int id;
    private int votanteId;
    private int candidatiId;
    private String timeStamp;
    public Voto(int id, int votanteId, int candidatiId, String timeStamp){
        this.id = id;
        this.votanteId = votanteId;
        this.candidatiId = candidatiId;
        this.timeStamp = timeStamp;
    }
    public int getId(){return id;}
    public int getVotanteId(){return votanteId;}
    public int getCandidatiId(){return candidatiId;}
    public String getTimeStamp(){return timeStamp;}
    public void setId(int id){this.id = id;}
    public void setVotanteId(int votanteId){this.votanteId = votanteId;}
    public void setCandidatiId(int candidatiId){this.candidatiId = candidatiId;}
    public void setTimeStamp(String timeStamp){this.timeStamp = timeStamp;}
    }
    public class Candidato{
    private int id;
    private String nombre;
    private String partido;
    private Queue<Voto> votoRecibidos;
    public Candidato(int id, String nombre, String partido){
        this.id = id;
        this.nombre = nombre;
        this.partido = partido;
    }
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getPartido(){return partido;}
    public void setId(int id){this.id = id;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setPartido(String partido){this.partido = partido;}
    public void agregarVoto(Voto voto){votoRecibidos.add(voto);}    
    }
    public class Votante{
    private int id;
    private String nombre;
    private boolean yaVoto;
    public Votante(int id, String nombre, boolean yaVoto){
        this.id = id;
        this.nombre = nombre;
        this.yaVoto = yaVoto;
    }
    public int getId(){return id;}
        public String getNombre(){return nombre;}
        public boolean getYaVoto(){return yaVoto;}
        public void setId(int id){this.id = id;}
        public void setNombre(String nombre){this.nombre = nombre;}
        public void marcarVotado()
    }
    public class UrnaElectoral{}
}
