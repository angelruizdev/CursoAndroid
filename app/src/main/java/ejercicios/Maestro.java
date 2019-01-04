package ejercicios;

public class Maestro implements InterfActividades,InterfComer{
 private String nombre;
 private int edad;
 private char sexo;
 protected String ocupacion;

 public Maestro(String nombre, int edad, char sexo,String ocupacion){
  this.nombre=nombre;
  this.edad=edad;
  this.sexo=sexo;
  this.ocupacion=ocupacion;
 }

 public String getNombre() {
  return nombre;
 }

 public void setNombre(String nombre) {
  this.nombre = nombre;
 }

 public int getEdad() {
  return edad;
 }

 public void setEdad(int edad) {
  this.edad = edad;
 }

 public char getSexo() {
  return sexo;
 }

 public void setSexo(char sexo) {
  this.sexo = sexo;
 }

 public void datos() {
  System.out.println(getNombre() + "\n" + getEdad() + "\n" + getSexo() + "\n" + ocupacion);
 }
 //pertenece a la interface InterfActividades, aquí lo implementamos.
 @Override
 public void hacerEjercicio() {
  System.out.println("Ir al gimnacio en las tardes");
 }
 //pertenecen a la interface InterfComer, aquí lo implementamos.
 @Override
 public String comer() {
  return "Comer proteina en las tardes";
 }
}
