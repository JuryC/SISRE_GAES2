/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Inventario;
import entidades.Material;
import entidades.MaterialCantidad;
import entidades.Tipomaterial;
import entidades.Usuario;
import facade.InventarioFacade;
import facade.MaterialCantidadFacade;
import facade.MaterialFacade;
import facade.TipomaterialFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jury
 */
@Named(value = "materialControlador")
@ManagedBean
@SessionScoped
public class MaterialControlador implements Serializable {

    /**
     * Creates a new instance of MaterialControlador
     */
    public MaterialControlador() {
    }
    private Material material;
    private Tipomaterial tipomaterial;
    private MaterialCantidad MT;
    private Usuario usuario;
    private Inventario inventario;
    private List<Material> materialList;
    private int materialId = 0;
    private String nombreMaterial;
    private String nombre;
    private Double valor = null;
    private Date fecha = new Date();
    private int cantidad;

    @PostConstruct
    public void init() {
        material = new Material();
        MT = new MaterialCantidad();
        inventario = new Inventario();
        tipomaterial = new Tipomaterial();
        usuario = new Usuario();
    }
    @Inject
    MensajeControlador mensaje;

    @EJB
    MaterialFacade materialFacade;

    @EJB
    MaterialCantidadFacade MTFacade;

    @EJB
    InventarioFacade inventarioFacade;

    @EJB
    TipomaterialFacade tipomaterialFacade;

    @EJB
    UsuarioFacade usuarioFacade;

    public void registrar() {
        if (materialId != 0) {//En caso de que se elija un material existente
            Material mat = materialFacade.obtenerxId(materialId);//Se busca el material con el id que seleccionó
            //Se realiza el registro en la tabla MaterialCantidad
            MT.setCantidad(cantidad);
            MT.setFechaIngreso(fecha);//Se asigna la fecha actual obtenida por el metodo Date()
            MT.setMaterial(mat);//Se asigna el material resultado de la búsqueda
            MTFacade.create(MT);//Se registra la entidad
            //Se realiza la actualización en la tabla Inventario
            inventario = inventarioFacade.obtenerValor(materialId);
            int cantTotal = MT.getCantidad() + inventario.getExistencia();
            inventario.setExistencia(cantTotal);
            inventario.setIdUsuario(usuario);
            if (valor != null) {
                inventario.setValor(valor);
            }
            inventarioFacade.edit(inventario);
            mensaje.setMensaje("MensajeRedirect('ListarInventario.xhtml','Registro','El registro ha sido exitoso','success');");
            this.inventario = null;
            this.usuario = null;
            this.MT = null;
            this.valor = null;
            this.nombreMaterial = "";
            this.materialList = null;
            this.materialId = 0;
            this.cantidad = 0;
            this.nombre = "";
            this.material = null;

        } else {
            Material mat = materialFacade.obtenerxNombre(nombre);
            if (mat == null) {
                material.setIdTipoMaterial(tipomaterial);
                material.setEstado(1);
                material.setNombre(nombre);
                materialFacade.create(material);
                //Se realiza el registro en la tabla MaterialCantidad
                MT.setCantidad(cantidad);
                MT.setFechaIngreso(fecha);//Se asigna la fecha actual obtenida por el metodo Date()
                MT.setMaterial(material);//Se asigna el material resultado de la búsqueda
                MTFacade.create(MT);//Se registra la entidad
                //Se realiza el registro en la tabla inventario
                inventario.setArticulo(material);
                inventario.setEstado(1);
                inventario.setExistencia(MT.getCantidad());
                inventario.setIdUsuario(usuario);
                inventario.setValor(valor);
                inventarioFacade.create(inventario);
                mensaje.setMensaje("MensajeRedirect('ListarInventario.xhtml','Registro','El registro ha sido exitoso','success');");
                this.material = new Material();
                this.tipomaterial = null;
                this.usuario = null;
                this.cantidad = 0;
                this.valor = null;
                this.nombre = null;
            }
        }

    }

    public List<Material> getListByMaterial() {
        if (materialList == null) { //si es la primera vez que se accederá a la lista...
            materialList = null; //... actualizar el contenido
        }
        return materialList; //devuelve la lista con los elementos encontrados
    }

    public void changeListMaterial(AjaxBehaviorEvent event) {
        if (!"".equals(nombreMaterial)) {
            materialList = materialFacade.findByName(nombreMaterial);
        } else {
            this.materialList = null;
        }
    }

    public void rellenar(AjaxBehaviorEvent event) {
        if (materialId != 0) {
            Material mat = materialFacade.obtenerxId(materialId);
            this.nombre = mat.getNombre();
            this.material.setDescripcion(mat.getDescripcion());
            this.valor = inventarioFacade.obtenerValor(mat.getIdMaterial()).getValor();
            this.tipomaterial.setIdTipoMaterial(mat.getIdTipoMaterial().getIdTipoMaterial());
        }else{
            this.nombre = "";
            this.material = null;
            this.valor = null;
            this.tipomaterial = null;
        }
    }

    public List<Material> consultarTodos() {
        return materialFacade.findAll();
    }

    public String preActualizar(Material materialActualizar, Inventario inventarioActualizar) {
        material = materialActualizar;
        tipomaterial = materialActualizar.getIdTipoMaterial();
        inventario = inventarioActualizar;
        return "ActualizarMaterial";
    }

    public void actualizar() {
        material.setIdTipoMaterial(tipomaterial);
        materialFacade.edit(material);
        mensaje.setMensaje("MensajeRedirect('ListarMaterial.xhtml','Actualizacion','La actualizacion se ha realizado con exito','success');");
    }

    public void eliminar(Material materialEliminar) {
        material = materialEliminar;
        material.setEstado(2);
        materialFacade.edit(material);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Tipomaterial getTipomaterial() {
        return tipomaterial;
    }

    public void setTipomaterial(Tipomaterial tipomaterial) {
        this.tipomaterial = tipomaterial;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public MaterialCantidad getMT() {
        return MT;
    }

    public void setMT(MaterialCantidad MT) {
        this.MT = MT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
