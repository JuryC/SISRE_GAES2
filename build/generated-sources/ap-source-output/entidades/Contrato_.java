package entidades;

import entidades.Contenido;
import entidades.Tiposervicio;
import entidades.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Contrato.class)
public class Contrato_ { 

    public static volatile SingularAttribute<Contrato, Usuario> creadoPor;
    public static volatile SingularAttribute<Contrato, Integer> estado;
    public static volatile ListAttribute<Contrato, Contenido> contenidoList;
    public static volatile SingularAttribute<Contrato, Date> fechaCreacion;
    public static volatile SingularAttribute<Contrato, Integer> idContrato;
    public static volatile SingularAttribute<Contrato, String> seleccion;
    public static volatile SingularAttribute<Contrato, Tiposervicio> idTipoServicio;

}