package entidades;

import entidades.Servicio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, Integer> estado;
    public static volatile SingularAttribute<Solicitud, Date> fechaSolicitud;
    public static volatile SingularAttribute<Solicitud, String> direccionInicio;
    public static volatile SingularAttribute<Solicitud, String> admision;
    public static volatile SingularAttribute<Solicitud, Integer> idSolicitud;
    public static volatile SingularAttribute<Solicitud, String> destino;
    public static volatile SingularAttribute<Solicitud, Servicio> idServicio;
    public static volatile SingularAttribute<Solicitud, Date> fechaResolucion;

}