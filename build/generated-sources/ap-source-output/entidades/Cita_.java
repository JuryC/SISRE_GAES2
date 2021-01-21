package entidades;

import entidades.CitaMaterial;
import entidades.Cliente;
import entidades.Servicio;
import entidades.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Cita.class)
public class Cita_ { 

    public static volatile SingularAttribute<Cita, Integer> idCita;
    public static volatile SingularAttribute<Cita, String> descripcion;
    public static volatile SingularAttribute<Cita, Date> fechaCita;
    public static volatile SingularAttribute<Cita, Integer> estado;
    public static volatile ListAttribute<Cita, CitaMaterial> citaMaterialList;
    public static volatile SingularAttribute<Cita, Date> fechaSolicitud;
    public static volatile SingularAttribute<Cita, Date> horaCita;
    public static volatile SingularAttribute<Cita, String> origen;
    public static volatile ListAttribute<Cita, Servicio> servicioList;
    public static volatile SingularAttribute<Cita, Double> cotizacion;
    public static volatile SingularAttribute<Cita, Cliente> idCliente;
    public static volatile SingularAttribute<Cita, Usuario> operarioCargo;
    public static volatile SingularAttribute<Cita, String> observaciones;
    public static volatile SingularAttribute<Cita, String> destino;

}