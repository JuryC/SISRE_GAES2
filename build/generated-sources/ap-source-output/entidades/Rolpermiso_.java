package entidades;

import entidades.Permiso;
import entidades.Rol;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Rolpermiso.class)
public class Rolpermiso_ { 

    public static volatile SingularAttribute<Rolpermiso, Integer> estado;
    public static volatile SingularAttribute<Rolpermiso, Rol> idRol;
    public static volatile SingularAttribute<Rolpermiso, Permiso> idPermiso;
    public static volatile SingularAttribute<Rolpermiso, Integer> idRolPermiso;

}