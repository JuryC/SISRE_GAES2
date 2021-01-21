package entidades;

import entidades.Material;
import entidades.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Inventario.class)
public class Inventario_ { 

    public static volatile SingularAttribute<Inventario, Integer> idArticulo;
    public static volatile SingularAttribute<Inventario, Integer> existencia;
    public static volatile SingularAttribute<Inventario, Integer> estado;
    public static volatile SingularAttribute<Inventario, Usuario> idUsuario;
    public static volatile SingularAttribute<Inventario, Double> valor;
    public static volatile SingularAttribute<Inventario, Material> articulo;

}