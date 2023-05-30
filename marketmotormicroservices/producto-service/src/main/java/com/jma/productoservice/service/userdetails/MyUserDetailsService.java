package com.jma.productoservice.service.userdetails;

/*
@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioService<UsuarioDto> usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioDto usuario= usuarioService.getUsuarioByAlias(username);
        if(usuario==null){
            return null;
        }
        GrantedAuthority rol = new SimpleGrantedAuthority(usuario.getRol().getNombre());
        List<GrantedAuthority> listaRoles = List.of(rol);

        return new User(usuario.getAlias(), usuario.getContrasena(), listaRoles);
    }

}
*/