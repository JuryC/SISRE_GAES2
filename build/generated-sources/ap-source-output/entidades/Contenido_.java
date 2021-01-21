package entidades;

import entidades.Contenido;
import entidades.Contrato;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Contenido.class)
public class Contenido_ { 

    public static volatile SingularAttribute<Contenido, String> contenido;
    public static volatile SingularAttribute<Contenido, Integer> idParrafo;
    public static volatile ListAttribute<Contenido, Contenido> contenidoList;
    public static volatile SingularAttribute<Contenido, String> titulo;
    public static volatile SingularAttribute<Contenido, Integer> orden;
    public static volatile SingularAttribute<Contenido, Contenido> idTitulo;
    public static volatile SingularAttribute<Contenido, Contrato> idContrato;
    public static volatile SingularAttribute<Contenido, String> itemLista;

}