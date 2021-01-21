/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cliente;
import entidades.Rol;
import entidades.Usuario;
import facade.ClienteFacade;
import facade.RolFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.security.NoSuchProviderException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

/**
 *
 * @author Jury
 */
@Named(value = "usuarioControlador")
@ManagedBean
@SessionScoped
public class UsuarioControlador implements Serializable {

    /**
     * Creates a new instance of UsuarioControlador
     */
    public UsuarioControlador() {
    }
    Usuario usuario;
    Rol rol;
    private int selectRol;
    private String pasaporte;
    private String documento;
    private Cliente cliente;
    CorreoControlador email;
    private Date fecha = new Date();

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        rol = new Rol();
        cliente = new Cliente();
    }
    @Inject
    MensajeControlador mensaje;
    @EJB
    UsuarioFacade usuarioFacade;

    @EJB
    RolFacade rolFacade;

    @EJB
    ClienteFacade clienteFacade;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void registrar() {
        int doc = Integer.parseInt(documento);
        Usuario usuarioPrueba = usuarioFacade.validarDocumento(doc);
        if (usuarioPrueba == null) {
            rol = rolFacade.obtenerRol(selectRol);
            usuario.setIdRol(rol);
            usuario.setEstado(1);
            usuario.setDocumento(doc);
            usuario.setAvatar("assets/avatar/avatarDefault.jpg");
            usuario.setFechaNacimiento(fecha(usuario.getFechaNacimiento()));
            usuario.setFechaIngreso(fecha(usuario.getFechaIngreso()));
            if (selectRol == 2) {
                usuarioFacade.create(usuario);
                usuario.setFechaNacimiento(fecha(usuario.getFechaNacimiento()));
                usuario.setFechaIngreso(fecha(usuario.getFechaIngreso()));
                cliente.setIdCliente(usuario.getIdUsuario());
                cliente.setPasaporte(pasaporte);
                cliente.setEstado(1);
                clienteFacade.create(cliente);
            } else {
                usuarioFacade.create(usuario);
            }
            mensaje.setMensaje("MensajeRedirect('ListarUsuario.xhtml','Registro','El registro ha sido exitoso','success');");

            this.cliente = null;
            this.pasaporte = "";
            this.documento = "";
            this.selectRol = 1;
            this.usuario = new Usuario();
            this.rol = null;
        } else {
            mensaje.setMensaje("MensajeRedirect('ListarUsuario.xhtml','Error','Ya existe un usuario registrado con el número documento digitado','error');");

        }

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

    public String preActualizar(Usuario usuarioActualizar) {
        usuario = usuarioActualizar;
        rol = usuarioActualizar.getIdRol();
        return "ActualizarUsuario";
    }

    public void actualizar() {
        usuario.setIdRol(rol);
        usuarioFacade.edit(usuario);
        mensaje.setMensaje("MensajeRedirect('ListarUsuario.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }

    public void eliminar(Usuario usuarioEliminar) {
        usuario = usuarioEliminar;
        usuario.setEstado(2);
        usuarioFacade.edit(usuario);
    }

    public List<Usuario> consultarTodos() {
        return usuarioFacade.consultarTodos(1);
    }

    //Metodo que ejecuta la consulta para obtener un usuario
    public List<Usuario> obtenerUsuario(int id) {
        return usuarioFacade.obtenerUsuario(id);
    }

    //Método que ejecuta la consulta para obtener los usuario de un rol en específico
    public List<Usuario> obtenerUsuarioRol(int rol) {
        return usuarioFacade.obtenerUsuarioRol(rol);
    }

    public void notificarM() throws NoSuchProviderException, MessagingException {
        consultarTodos();
        for (Usuario usu : consultarTodos()) {
            email.enviarEmail(usu.getEmail(), "Notificacion", "Bienvenido a Multimudanzas Cargo Ltda ");
        }
    }

    public int getSelectRol() {
        return selectRol;
    }

    public void setSelectRol(int selectRol) {
        this.selectRol = selectRol;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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
}
