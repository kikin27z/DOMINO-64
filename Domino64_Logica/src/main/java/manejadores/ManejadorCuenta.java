package manejadores;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Avatar;
import entidades.Cuenta;
import entidadesDTO.AvatarDTO;
import entidadesDTO.CuentaDTO;
import java.util.List;

/**
 *
 * @author karim
 */
public class ManejadorCuenta {
    private final AdaptadorEntidad adapterEntidad;
    private final AdaptadorDTO adapterDTO;
    private Cuenta cuenta;

    public ManejadorCuenta() {
        cuenta = new Cuenta();
        adapterEntidad = new AdaptadorEntidad();
        adapterDTO = new AdaptadorDTO();
    }
    
    public void asignarCuenta(CuentaDTO cuentaDTO){
        this.cuenta = adapterDTO.adaptarCuentaDTO(cuentaDTO);
    }
    

    public void asignarAvatar(AvatarDTO avatarDTO){
        Avatar avatar = adapterDTO.adaptarAvatarDTO(avatarDTO);
        cuenta.setAvatar(avatar);
        
    }

        public CuentaDTO getCuenta() {
        return adapterEntidad.adaptarEntidadCuenta(cuenta);
    }
    
    public void borrarPerfil(){
        this.cuenta.setAvatar(null);
        this.cuenta.setAdmin(false);
    }
    
}
