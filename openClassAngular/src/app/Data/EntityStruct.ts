export class categoria{
    categoriaId:number;
    categoriaNombre:string;
    categoriaEliminado:boolean;
}

export class clase{
    claseID:number;
    curso:curso;
    claseDescripcion:string;
    claseNombre:string;
    claseUrl:string;
    claseEliminado:boolean;
}

export class comentario{
    comentarioID:number;
    clase:clase;
    usuario:usuario;
    comentarioDetalle:string;
    comentarioFecha:Date;
    comentarioEstado:boolean;
}

export class curso{
    cursoId:number;
    cursoNombre:string;
    creador:usuario;
    categoria:categoria;
    cursoFechaInicio:Date;
    cursoNumeroSesiones:number;
    cursoMinuatura:string;
    cursoEstado:string;
}

export class cursoRegistro {
    cursoRegistroId:number;
    alumno:usuario;
    curso:curso;
    cursoRegistrooEstado:string;
    cursoRegistroEliminado:boolean;
}

export class usuario{
    usuarioId:number;
    usuarioNombres:string;
    usuarioApellidos:string;
    usuarioTipo:string;
    usuarioCorreo:string;
    usuarioPassword:string;
    usuarioInstitucion:string;
    usuarioEliminado:boolean;
    usuarioFoto:string;
}