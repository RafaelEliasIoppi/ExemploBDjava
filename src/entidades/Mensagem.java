package entidades;




public  class  Mensagem {

    private int codigo;
    private String nome;
    private String setor;
    private String destinatario;
    private String mensagem;
    private String dataEnvio;
    private String status;


    public Mensagem() {
    }
    public Mensagem(String nome, String setor, String destinatario, String mensagem, String dataEnvio2) {
        this.nome = nome;
        this.setor = setor;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
        this.dataEnvio =  dataEnvio2;
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDataEnvio() {
        return  dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

