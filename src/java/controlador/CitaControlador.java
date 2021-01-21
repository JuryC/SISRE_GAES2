/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cita;
import entidades.CitaMaterial;
import entidades.Cliente;
import entidades.Envio;
import entidades.Inventario;
import entidades.Material;
import entidades.Servicio;
import entidades.Tiposervicio;
import entidades.Usuario;
import facade.CitaFacade;
import facade.CitaMaterialFacade;
import facade.ClienteFacade;
import facade.EnvioFacade;
import facade.InventarioFacade;
import facade.ServicioFacade;
import facade.TiposervicioFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.security.NoSuchProviderException;
import java.text.DateFormat;import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

/**
 *
 * @author Jury
 */
@Named(value = "citaControlador")
@ManagedBean
@SessionScoped
public class CitaControlador implements Serializable {

    /**
     * Creates a new instance of CitaControlador
     */
    public CitaControlador() {
    }
    private Cita cita;
    private CitaMaterial CM;
    private Cliente cliente;
    private Usuario usuario;
    private Servicio servicio;
    private Envio envio;
    private Tiposervicio tipo;
    private Date fecha = new Date();
    private List<Usuario> operariosDisponibles;
    private int cant = 1;
    private Double cotizacion;
    private Double totalServicio;
    private int tipoServicio;
    private int tipoEnvio;
    private int idCliente;
    private String lenguaje;
    private int id;

    @PostConstruct
    public void init() {
        cliente = new Cliente();
        cita = new Cita();
        usuario = new Usuario();
        CM = new CitaMaterial();
        servicio = new Servicio();
        envio = new Envio();
        tipo = new Tiposervicio();
    }
    @Inject
    MensajeControlador mensaje;
    
    @Inject
    CorreoControlador correo;
    

    @EJB
    CitaFacade citaFacade;

    @EJB
    ClienteFacade clienteFacade;

    @EJB
    UsuarioFacade usuarioFacade;

    @EJB
    CitaMaterialFacade CMFacade;

    @EJB
    InventarioFacade invFacade;

    @EJB
    ServicioFacade servFacade;

    @EJB
    TiposervicioFacade tipoFacade;

    @EJB
    EnvioFacade envioFacade;
    

    //Metodo para obtener el idioma a traducir
    public void lenguaje(String lg) {
        this.lenguaje = lg;
    }

    //Cliente
    public void registrar() throws NoSuchProviderException, MessagingException {
        Cita citaPrueba = citaFacade.validarCita(id);
        if(citaPrueba == null){
        cita.setIdCliente(clienteFacade.find(this.idCliente));
        cita.setFechaSolicitud(fecha);
        cita.setEstado(1);
        citaFacade.create(cita);
        cita.setFechaCita(fecha(cita.getFechaCita()));
        cita.setHoraCita(hora(cita.getHoraCita()));
        citaFacade.edit(cita);
        correo.setAsunto("Cita Agendada");
        correo.setCuerpo("Se ha registrado con exito su cita, pronto le enviaremos más información de su servicio");
        correo.enviarEmail(cita.getIdCliente().getUsuario().getEmail(),"Cita Agendada",correo.pagina());

        
        
        if ("es".equals(lenguaje)) {
            mensaje.setMensaje("MensajeRedirect('ListarCita.xhtml','Registro exitoso','El registro ha sido exitoso','success');");
        } else {
            mensaje.setMensaje("MensajeRedirect('ListarCita.xhtml','Successful registration','The registration has been successful','success');");
        }

        this.cita = new Cita();
        this.usuario = null;
        this.cliente = null;

    }else{
            mensaje.setMensaje("MensajeRedirect('ListarCita.xhtml','Error','Ya existe una cita registrada con el id digitado','error');");
        }
    
    }
    
    
    public String nowFormat() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(fecha(date));
    }

    public Date fecha(Date fecha) {
        Calendar f = Calendar.getInstance();
        f.setTime(fecha);
        f.add(Calendar.DATE, 1);
        return f.getTime();
    }
    public String convertirf(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(fecha);
    }
    
    public Date hora(Date hora){
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(hora);
        c.add(Calendar.HOUR, 5);
        return c.getTime();
        
    }
    
    public String convertir(Date hora){
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(hora);
    }
    
    public String preActualizar(Cita citaActualizar) {
        cliente = citaActualizar.getIdCliente();
        cita = citaActualizar;
        return "ActualizarCita";
    }

    public void actualizar() {
        cita.setIdCliente(cliente);
        citaFacade.edit(cita);
        mensaje.setMensaje("MensajeRedirect('ListarCita.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");

    }

    //Supervisor
    /*Metodo que filtra una lista de operarios para mostrar solo aquellos a quienes
    que no tengan citas en ese mismo horario*/
   public String supervisor(Cita citaActualizar) {
        cliente = citaActualizar.getIdCliente();
        cita = citaActualizar;
        List<Usuario> operarios = usuarioFacade.obtenerUsuarioRol(4);
        this.operariosDisponibles = new ArrayList<>();
        for (int i = 0; i < operarios.size(); i++) {
            List<Cita> citas = citaFacade.consultarCitasOp(operarios.get(i).getIdUsuario(), fecha);
            for (int j = 0; j < citas.size(); j++) {
                if (citas.get(j).getFechaCita() != cita.getFechaCita()) {
                    this.operariosDisponibles.add(operarios.get(i));
                    for (int k = 1; k < this.operariosDisponibles.size(); k++) {
                        if(this.operariosDisponibles.get(k-1)==this.operariosDisponibles.get(k)){
                            this.operariosDisponibles.remove(k);
                        }
                    }
                }
            }
        }
        return "AsignarOperario";
    }

    public void asignarOperario() {
        cita.setIdCliente(cliente);
        cita.setOperarioCargo(usuario);
        citaFacade.edit(cita);
        mensaje.setMensaje("MensajeRedirect('ListarCita.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }

    //Operario
    public String operario(Cita citaActualizar) {
        cliente = citaActualizar.getIdCliente();
        usuario = citaActualizar.getOperarioCargo();
        cita = citaActualizar;
        return "RegistrarCita";
    }

    public void detallesCita(int id) {
        cita.setIdCliente(cliente);
        cita.setOperarioCargo(usuario);
        calcularTotal(id);
        cita.setCotizacion(cotizacion);
        citaFacade.edit(cita);
        servicio.setEstado(1);
        servicio.setIdCita(cita);
        servicio.setEstadoServicio("Solicitado");
        servicio.setIdEnvio(envio);
        servicio.setTipoServicio(tipo);
        servicio.setTotal(totalServicio);
        servFacade.create(servicio);
        mensaje.setMensaje("MensajeRedirect('ListarCita.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }

    public void registrarCM(Cita citaActual, Material materialSeleccionado) {
        CitaMaterial CM2 = CMFacade.validarSolicitud(citaActual.getIdCita(), materialSeleccionado.getIdMaterial());
        if (CM2 == null) {
            CM.setIdCita(citaActual);
            CM.setIdMaterial(materialSeleccionado);
            CM.setCantidadSolicitada(cant);
            CMFacade.create(CM);
            Inventario inv = invFacade.obtenerValor(CM.getIdMaterial().getIdMaterial());
            int newCant = inv.getExistencia() - cant;
            inv.setExistencia(newCant);
            invFacade.edit(inv);
            mensaje.setMensaje("MensajeToast('Material agregado','success');");
            this.cant = 1;
        } else {
            int total = CM2.getCantidadSolicitada() + cant;
            CM2.setCantidadSolicitada(total);
            Inventario inv = invFacade.obtenerValor(CM2.getIdMaterial().getIdMaterial());
            if (inv.getExistencia() != 0) {
                CMFacade.edit(CM2);
                int newCant = inv.getExistencia() - cant;
                inv.setExistencia(newCant);
                invFacade.edit(inv);
            } else {
                mensaje.setMensaje("MensajeToast('Limite alcanzado','error');");
            }
            mensaje.setMensaje("MensajeToast('Material agregado','success');");
            this.cant = 1;
        }
    }

    public Double obtenerValorTotalMaterial(int idCita, int idMaterial) {
        CitaMaterial CMS = CMFacade.validarSolicitud(idCita, idMaterial);
        double total = 0;
        Inventario inv = invFacade.obtenerValor(CMS.getIdMaterial().getIdMaterial());
        total = inv.getValor() * CMS.getCantidadSolicitada();
        return total;

    }

    public void eliminarSolicitudMaterial(CitaMaterial CMEliminar) {
        CM = CMEliminar;
        CMFacade.remove(CM);
        Inventario inv = invFacade.obtenerValor(CM.getIdMaterial().getIdMaterial());
        inv.setExistencia(inv.getExistencia() + CM.getCantidadSolicitada());
        invFacade.edit(inv);
        mensaje.setMensaje("MensajeToast('Material retirado correctamente','success');");
    }

    public Double calcularCotizacion(int id) {
        List<CitaMaterial> CMList = CMFacade.obtenerMateriales(id);
        double total = 0;
        for (int i = 0; i < CMList.size(); i++) {
            Inventario inv = invFacade.obtenerValor(CMList.get(i).getIdMaterial().getIdMaterial());
            total = total + (inv.getValor() * CMList.get(i).getCantidadSolicitada());
        }
        cotizacion = total;
        return cotizacion;
    }

    public Double calcularTotal(int id) {
        calcularCotizacion(id);
        envio = envioFacade.obtenerEnvio(tipoEnvio);
        tipo = tipoFacade.obtenerTiposervicio(tipoServicio);
        double totalisimo = cotizacion + tipo.getValor() + envio.getValor();
        totalServicio = totalisimo;
        return totalServicio;
    }

    public void eliminar(Cita citaEliminar) {
        cita = citaEliminar;
        cita.setEstado(2);
        citaFacade.edit(cita);
    }

    public List<Cita> consultarTodos() {
        return citaFacade.consultarTodos(1);
    }

    public List<Cita> consultarCitaUsuario(int id) {
        return citaFacade.consultarCitasUsuario(id);
    }

    public List<Cita> consultarCitaOperario(int id) {
        return citaFacade.consultarCitasOp(id, fecha);
    }

    public List<CitaMaterial> obtenerMateriales(int id) {
        return CMFacade.obtenerMateriales(id);
    }

    public List<Cita> consultarCitaOpNull() {
        return citaFacade.consultarCitasOpNull();
    }

    public int cantidadCitas(int id) {
        return citaFacade.contarCitaxOp(id);
    }

    public List<Tiposervicio> consultarTipoServ() {
        return tipoFacade.findAll();
    }

    // Getters and Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Usuario> getOperariosDisponibles() {
        return operariosDisponibles;
    }

    public void setOperariosDisponibles(List<Usuario> operariosDisponibles) {
        this.operariosDisponibles = operariosDisponibles;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public CitaMaterial getCM() {
        return CM;
    }

    public void setCM(CitaMaterial CM) {
        this.CM = CM;
    }

    public Double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Double getTotalServicio() {
        return totalServicio;
    }

    public void setTotalServicio(Double totalServicio) {
        this.totalServicio = totalServicio;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(int tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public Tiposervicio getTipo() {
        return tipo;
    }

    public void setTipo(Tiposervicio tipo) {
        this.tipo = tipo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}