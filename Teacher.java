package teachers;

public class Teacher implements ITeacher {

	private Id _id; 
	private Person person;
	
	
	public Teacher() {
		this._id = new Id(); 
		this.person = new Person();
	}
	
	@Override
	public String get_id() {       
		return this._id.get_id();
	}

	@Override
	public void set_id(String id) {     
		this._id.set_id(id);
	}	
	
	/**
	 * @param person - Person
	 */
	public Teacher(Person person) {
		this.person = person;
	}

	//Person
	
	@Override
	public String getName() {
		return person.getName();
	}

	@Override
	public void setName(String Name) {
		person.setName(Name);
	}

	@Override
	public String getDni() {
		return person.getDni(); 
	}

	@Override
	public void setDni(String dni) {
		person.setDni(dni);
		
	}
	//End Person

	@Override
	public String toString() {
		return "Teacher [_id="+_id+", person=" + person.toString() + "]";
	}

}