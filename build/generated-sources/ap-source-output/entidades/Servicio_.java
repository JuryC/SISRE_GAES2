package entidades;

import entidades.Cita;
import entidades.Envio;
import entidades.Solicitud;
import entidades.Tiposervicio;
import entidades.Transporte;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, Cita> idCita;
    public static volatile SingularAttribute<Servicio, String> estadoServicio;
    public static volatile SingularAttribute<Servicio, Integer> estado;
    public static volatile SingularAttribute<Servicio, Double> total;
    public static volatile SingularAttribute<Servicio, Tiposervicio> tipoServicio;
    public static volatile SingularAttribute<Servicio, Transporte> idTransporte;
    public static volatile SingularAttribute<Servicio, Integer> idServicio;
    public static volatile SingularAttribute<Servicio, Envio> idEnvio;
    public static volatile ListAttribute<Servicio, Solicitud> solicitudList;

}