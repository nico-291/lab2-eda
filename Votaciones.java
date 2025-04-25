import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Votaciones{
    public class Voto{
    private int id;
    private int votanteId;
    private int candidatoId;
    private String timeStamp;
    public Voto(int id, int votanteId, int candidatoId){
        this.id = id;
        this.votanteId = votanteId;
        this.candidatoId = candidatoId;
        this.timeStamp = timeStamp;
    }
    public int getId(){return id;}
    public int getVotanteId(){return votanteId;}
    public int getCandidatoId(){return candidatoId;}
    public String getTimeStamp(){return timeStamp;}
    public void setId(int id){this.id = id;}
    public void setVotanteId(int votanteId){this.votanteId = votanteId;}
    public void (int candidatoId){this.candidatoId = candidatoId;}
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
            votoRecibidos = new LinkedList<>();
    }
        public int getId(){return id;}
        public String getNombre(){return nombre;}
        public String getPartido(){return partido;}
        public Queue<Voto> getVotosRecibidos(){return votoRecibidos;}
        public void setId(int id){this.id = id;}
        public void setNombre(String nombre){this.nombre = nombre;}
        public void setPartido(String partido){this.partido = partido;}
        public void agregarVoto(Voto v){votoRecibidos.add(v);}
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
        public void setYaVoto(boolean yaVoto){this.yaVoto = yaVoto;}
        public void marcarVotado(){
            this.yaVoto = true;
        }

    }


    public class UrnaElectoral{
        private LinkedList <Candidato> Listacandidatos;
        private Stack <Voto> historialVotos;
        private Queue<Voto> votosReportados;
        private int idCounter;
        public UrnaElectoral(){
            this.Listacandidatos = new LinkedList<>();
            this.historialVotos = new Stack<>();
            this.votosReportados = new LinkedList<>();
            this.idCounter = 1;
        }

        public void agregarCandidato(Candidato candidato){Listacandidatos.add(candidato)}
        public boolean verificarVotante(Votante votante){return votante.getYaVoto();}

        public boolean registrtarVoto(Votante votante, int candidatoId){
            if(verificarVotante(votante)){
                System.out.println("El votante"+ votante.getId() + " ya ah votado");
            }
            for (Candidato candidato : Listacandidatos) {
                if (candidato.getId() == candidatoId) {
                    Voto nuevoVoto=new Voto(idCounter++,votante.getId(),candidatoId);
                    candidato.agregarVoto(nuevoVoto);
                    historialVotos.add(nuevoVoto);
                    votante.marcarVotado();
                    System.out.println("Voto registrado para el votante"+ candidato.getNombre());
                }
                System.out.println("candidato no encontrado");
                return false;
            }
        }
        public boolean reportarVoto(Candidato candidato,int idVoto){
            Queue<Voto> votosCandidato= candidato.getVotosRecibidos();
            Voto votoReportado=null;
            for(Voto voto : votosCandidato){
                if(voto.getId() == idVoto){
                    votoReportado=voto;
                    break;
                }
            }
            if (votoReportado!=null){
                votosCandidato.remove(votoReportado);
                votosReportados.add(votoReportado);
                System.out.println("Voto reportado registrado");
            }else {
                System.out.println("Voto no encontrado en el registro");
            }
        }
        public Map<String,Integer> obtenerResultado(){

        }
    }
}
