package ente.zombi.visitor;

import ente.plantas.*;
import ente.proyectiles.Proyectil;

public interface Visitor {
	
	public void visitLanzaGuisantes(LanzaGuisantes p);
	
	public void visitGirasol(Girasol p);
	
	public void visitJalapeno(Jalapeno p);
	
	public void visitSetaDesporada(SetaDesporada p);
	
	public void visitSetaSolar(SetaSolar p);
	
	public void visitHumoseta(Humoseta p);
	
	public void visitNuez(Nuez p);
	
	public void visitProyectil(Proyectil p);
}
