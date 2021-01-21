/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Contenido;
import entidades.Contrato;
import entidades.Tiposervicio;
import entidades.Usuario;
import facade.ContenidoFacade;
import facade.ContratoFacade;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author kesgr
 */
@Named(value = "utilidadesControlador")
@ManagedBean
@SessionScoped
public class UtilidadesControlador implements Serializable {

    public UtilidadesControlador() {
        contrato = new Contrato();
        contenido = new Contenido();
        tipoS = new Tiposervicio();
        usuario = new Usuario();
    }

    private Contrato contrato;
    private Contenido contenido;
    private Usuario usuario;
    private Tiposervicio tipoS;
    private String titulo;
    private String parrafo;
    private String itemsList;
    private int cantItems = 0;
    private int orden = 1;
    private int ordenPL = 1;
    private int cont;
    private String elementoElegido;
    private int idtitulo = 0;
    private Date fecha = new Date();
    private List<Contenido> titulosList;
    
    

    @EJB
    ContratoFacade contratoFacade;

    @EJB
    ContenidoFacade contenidoFacade;

    @Inject
    MensajeControlador mensaje;

    @Inject
    sesionControlador sesion;

    //----- Methods -----
    public void crearContrato() {
        contrato.setIdTipoServicio(tipoS);
        contrato.setEstado(1);
        contrato.setCreadoPor(usuario);
        contrato.setSeleccion("No Seleccionado");
        contrato.setFechaCreacion(fecha);
        contratoFacade.create(contrato);
        this.contrato = null;
        this.tipoS = null;
    }

    public void agregarElemento() {
        contrato = contratoFacade.obtenerContrato(cont);
        if (contrato != null) {
            if (!"".equals(titulo)) {
                contenido.setIdContrato(contrato);
                contenido.setTitulo(titulo);
                contenido.setOrden(orden);
                contenidoFacade.create(contenido);
                this.contenido = null;
                this.orden = orden + 1;
                this.contrato = null;
                this.titulo = "";
                this.parrafo = "";
            } else if (!"".equals(parrafo)) {
                if (idtitulo != 0) {
                    Contenido elTitulo = contenidoFacade.obtenerTitulo(idtitulo);
                    contenido.setContenido(parrafo);
                    contenido.setIdTitulo(elTitulo);
                    contenido.setIdContrato(contrato);
                    contenido.setOrden(ordenPL);
                    contenidoFacade.create(contenido);
                }
                this.contenido = null;
                this.ordenPL = ordenPL + 1;
                this.contrato = null;
                this.titulo = "";
                this.parrafo = "";
                this.idtitulo = 0;
            } else if (!"".equals(itemsList)) {
                if (idtitulo != 0) {

                    List<String> listItems = new ArrayList<>();
                    String item;
                    for (int i = 0; i < cantItems; i++) {
                        item = devuelveFrase2("<", ">", itemsList);
                        listItems.add(item);
                        String searchText = devuelveFrase1("<", ">", itemsList);
                        String newText = reemplazar(itemsList, searchText, "");
                        itemsList = newText;
                    }

                    Contenido elTitulo = contenidoFacade.obtenerTitulo(idtitulo);
                    
                    for (int i = 0; i < listItems.size(); i++) {
                        contenido.setItemLista(listItems.get(i+1));
                        contenido.setIdTitulo(elTitulo);
                        contenido.setIdContrato(contrato);
                        contenido.setOrden(i);
                        contenidoFacade.create(contenido);
                    }

                }
                this.contenido = null;
                this.contrato = null;
                this.titulo = "";
                this.parrafo = "";
                this.idtitulo = 0;
                this.itemsList = "";
                this.cantItems = 0;
            }

        }
    }

    //Metodos para la separación de los elementos de la lista
    public static String reemplazar(String cadena, String busqueda, String reemplazo) {
        return cadena.replaceAll(busqueda, reemplazo);
    }

    public static String devuelveFrase1(String palabraInicio, String palabraFinal, String texto) {
        String fraseCompleta;
        int indexPrimera = texto.indexOf(palabraInicio);
        int indexUltima = texto.indexOf(palabraFinal) + palabraFinal.length();

        fraseCompleta = texto.substring(indexPrimera, indexUltima);

        return fraseCompleta;
    }

    public static String devuelveFrase2(String palabraInicio, String palabraFinal, String texto) {
        String fraseCompleta;
        int indexPrimera = texto.indexOf(palabraInicio) + palabraInicio.length();
        int indexUltima = texto.indexOf(palabraFinal);

        fraseCompleta = texto.substring(indexPrimera, indexUltima);

        return fraseCompleta;
    }

    public void crearTitulo() {
        contrato = contratoFacade.obtenerContrato(cont);
        if (contrato != null) {
            contenido.setIdContrato(contrato);
            contenido.setTitulo(titulo);
            contenido.setOrden(orden);
            contenidoFacade.create(contenido);
            this.contenido = null;
            this.orden = orden + 1;
            this.contrato = null;
        }
    }

    public void crearParrafo() {
        Contenido parrafoBD = new Contenido();
        parrafoBD.setContenido(parrafo);
        parrafoBD.setIdTitulo(contenido);
        parrafoBD.setIdContrato(contrato);
        parrafoBD.setOrden(orden);
        contenidoFacade.create(parrafoBD);

        this.contenido = null;
        this.parrafo = "";
        this.titulo = "";

    }

    public void elementoSeleccionado(String element) {
        this.elementoElegido = element;
    }

    public List<Contrato> obtenerContratos(int id) {
        return contratoFacade.contratosPropios(id);
    }

    public void changeContrato(AjaxBehaviorEvent event) {
        if (cont != 0) {
            titulosList = new ArrayList<>();
            for (Contenido c : contenidoFacade.findAll()) {
                if (c.getIdContrato().getIdContrato() == cont && c.getTitulo() != null) {
                    titulosList.add(c);
                }
            }
        }
    }

    //Creación del word con el contrato
    //Previsualización
    
    
    //----- Getter and setter -----
    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public Tiposervicio getTipoS() {
        return tipoS;
    }

    public void setTipoS(Tiposervicio tipoS) {
        this.tipoS = tipoS;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getParrafo() {
        return parrafo;
    }

    public void setParrafo(String parrafo) {
        this.parrafo = parrafo;
    }

    public int getOrdenPL() {
        return ordenPL;
    }

    public void setOrdenPL(int ordenPL) {
        this.ordenPL = ordenPL;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public List<Contenido> getTitulosList() {
        return titulosList;
    }

    public void setTitulosList(List<Contenido> titulosList) {
        this.titulosList = titulosList;
    }

    public int getIdtitulo() {
        return idtitulo;
    }

    public void setIdtitulo(int idtitulo) {
        this.idtitulo = idtitulo;
    }

    public String getElementoElegido() {
        return elementoElegido;
    }

    public void setElementoElegido(String elementoElegido) {
        this.elementoElegido = elementoElegido;
    }

    public String getItemsList() {
        return itemsList;
    }

    public void setItemsList(String itemsList) {
        this.itemsList = itemsList;
    }

    public int getCantItems() {
        return cantItems;
    }

    public void setCantItems(int cantItems) {
        this.cantItems = cantItems;
    }

}
