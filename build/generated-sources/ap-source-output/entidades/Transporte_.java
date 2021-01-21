package entidades;

import entidades.Servicio;
import entidades.Tipotransporte;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Transporte.class)
public class Transporte_ { 

    public static volatile SingularAttribute<Transporte, String> marca;
    public static volatile SingularAttribute<Transporte, Integer> estado;
    public static volatile SingularAttribute<Transporte, String> disponibilidad;
    public static volatile SingularAttribute<Transporte, String> color;
    public static volatile SingularAttribute<Transporte, Integer> idTransporte;
    public static volatile SingularAttribute<Transporte, Tipotransporte> idTipoTransporte;
    public static volatile ListAttribute<Transporte, Servicio> servicioList;
    public static volatile SingularAttribute<Transporte, String> modelo;

}