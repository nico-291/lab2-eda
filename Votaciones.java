import java.util.*;

  class Votaciones{
    public class Voto{
        private int id;
        private int votanteId;
        private int candidatoId;
        private String timeStamp;
        public Voto(int id, int votanteId, int candidatoId){
            this.id = id;
            this.votanteId = votanteId;
            this.candidatoId = candidatoId;
            this.timeStamp = new java.util.Date().toString();
        }

        //get set
        public int getId(){
            return id;}
        public int getVotanteId(){
            return votanteId;}
        public int getCandidatoId(){
            return candidatoId;}
        public String getTimeStamp(){
            return timeStamp;}

        public void setId (int id){
            this.id = id;}
        public void setVotanteId (int votanteId){
            this.votanteId = votanteId;}
        public void setCandidatoId (int candidatoId){
            this.candidatoId = candidatoId;}
        public void setTimeStamp(String timeStamp){
            this.timeStamp = timeStamp;}
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
        //get set
        public int getId(){
            return id;}
        public String getNombre(){
            return nombre;}
        public String getPartido(){
            return partido;}
        public Queue<Voto> getVotosRecibidos(){
            return votoRecibidos;}

        public void setId(int id){
            this.id = id;}
        public void setNombre(String nombre){
            this.nombre = nombre;}
        public void setPartido(String partido){
            this.partido = partido;}
        public void agregarVoto(Voto v){
            votoRecibidos.add(v); }
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
        //get set
        public int getId(){
            return id;}
        public String getNombre(){
            return nombre;}
        public boolean getYaVoto(){
            return yaVoto;}

        public void setId(int id){
            this.id = id;}
        public void setNombre(String nombre){
            this.nombre = nombre;}
        public void setYaVoto(boolean yaVoto){
            this.yaVoto = yaVoto;}
        public void marcarVotado(){
            this.yaVoto = true;}
    }


    public class UrnaElectoral{
        public LinkedList <Candidato> Listacandidatos;
        private Stack <Voto> historialVotos;
        private Queue<Voto> votosReportados;
        private int idCounter;

        public UrnaElectoral(){
            this.Listacandidatos = new LinkedList<>();
            this.historialVotos = new Stack<>();
            this.votosReportados = new LinkedList<>();
            this.idCounter = 1;
        }

        public void agregarCandidato(Candidato candidato){
            Listacandidatos.add(candidato);
        }

        public boolean verificarVotante(Votante elvotante){
            return elvotante.getYaVoto();}

        public boolean registrarVoto(Votante votante, int candidatoId){

            if(verificarVotante(votante)){
                System.out.println("El votante"+ votante.getId() + " ya voto");
                return false;
            }
            for (int i=0; i<Listacandidatos.size(); i++) {
                Candidato candidato = Listacandidatos.get(i);

                if (candidato.getId() == candidatoId) {
                    Voto nuevoVoto=new Voto(idCounter++,votante.getId(),candidatoId);
                    candidato.agregarVoto(nuevoVoto);
                    historialVotos.add(nuevoVoto);
                    votante.marcarVotado();
                    System.out.println("EL voto a sido registrado (voto para)"+ candidato.getNombre());
                    return true;
                }

            }
            System.out.println("candidato no encontrado");
            return false;
        }

        public boolean reportarVoto(Candidato candidato,int idVoto){
            Queue<Voto> votosCandidato = candidato.getVotosRecibidos();
            List<Voto> listaVotos = new ArrayList<>(votosCandidato);
            Voto votoReportado = null;

            for(int i=0; i<votosCandidato.size(); i++){
                Voto voto = listaVotos.get(i);
                if(voto.getId() == idVoto){

                    votoReportado = voto;
                    break;
                }
            }

            if (votoReportado != null){
                votosCandidato.remove(votoReportado);
                votosReportados.add(votoReportado);
                System.out.println("Voto reportado registrado");
                return true;
            }else {
                System.out.println("Voto no encontrado");
                return false;
            }
        }

        public Map<String, Integer> obtenerResultados() {
            Map<String, Integer> resultados = new HashMap<>();

            for (int i = 0; i < Listacandidatos.size(); i++) {
                Candidato candidato = Listacandidatos.get(i);

                int totalVotos = candidato.getVotosRecibidos().size();

                resultados.put(candidato.getNombre(), totalVotos);
            }

            resultados.put("Votos Reportados", votosReportados.size());
            return resultados;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Votaciones sis = new Votaciones();
        Votaciones.UrnaElectoral urna = sis.new UrnaElectoral();

        while (true) {
            System.out.println("Seleccione una opcion");
            System.out.println("1. Registrar candidato");
            System.out.println("2. Votar");
            System.out.println("3. Reportar voto");
            System.out.println("4. Ver resultados");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");

            int opciones = scan.nextInt();
            scan.nextLine();

            switch (opciones) {
                case 1:
                    // Registrar los candidato
                    System.out.print("ID del candidato:");
                    int idCan = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Nombre del candidato: ");
                    String nomCan = scan.nextLine();

                    System.out.print("Partido: ");
                    String partido = scan.nextLine();

                    Votaciones.Candidato can = sis.new Candidato(idCan, nomCan, partido);
                    urna.agregarCandidato(can);
                    System.out.println("Candidato registrado");
                    break;

                case 2:
                    // Votacion
                    System.out.print("ID del votante: ");
                    int idVot = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Nombre del votante: ");
                    String nomVot = scan.nextLine();

                    System.out.println("Candidatos:");
                    for (int i = 0; i < urna.Listacandidatos.size(); i++) {
                        Votaciones.Candidato c = urna.Listacandidatos.get(i);
                        System.out.println(c.getId() + " - " + c.getNombre() + " (" + c.getPartido() + ")");
                    }

                    System.out.print("ID candidato: ");
                    int idCanSel = scan.nextInt();

                    Votaciones.Votante vot = sis.new Votante(idVot, nomVot, false);

                    if (urna.registrarVoto(vot, idCanSel)) {
                        System.out.println("Voto registrado");
                    }
                    break;

                case 3:
                    // Reportar voto
                    System.out.println("Candidatos:");
                    for (int i = 0; i < urna.Listacandidatos.size(); i++) {
                        Votaciones.Candidato c = urna.Listacandidatos.get(i);
                        System.out.println(c.getId() + " - " + c.getNombre() + " (" + c.getVotosRecibidos().size() + " votos)");
                    }

                    System.out.print("ID del candidato: ");
                    int idCanRep = scan.nextInt();

                    System.out.print("ID del voto: ");
                    int idVotRep = scan.nextInt();

                    Votaciones.Candidato reportado = null;
                    for (int i = 0; i < urna.Listacandidatos.size(); i++) {
                        if (urna.Listacandidatos.get(i).getId() == idCanRep) {
                            reportado = urna.Listacandidatos.get(i);
                            break;
                        }
                    }

                    if (reportado != null) {
                        if (urna.reportarVoto(reportado, idVotRep)) {
                            System.out.println("Voto reportado");
                        }
                    } else {
                        System.out.println("El candidato no existe");
                    }
                    break;

                case 4:
                    // Mostrar los resultados
                    System.out.println("Resultads");
                    Map<String, Integer> res = urna.obtenerResultados();

                    System.out.println("Votos por candidato:");
                    for (Map.Entry<String, Integer> entry : res.entrySet()) {
                        if (!entry.getKey().equals("Votos Reportados")) {
                            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
                        }
                    }

                    System.out.println("Votos irregulares: " + res.get("Votos Reportados"));
                    break;

                case 5:
                    // Salir
                    scan.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("La opcion no es valida");
            }
        }
    }
}
