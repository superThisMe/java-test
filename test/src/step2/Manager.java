package step2;

public interface Manager {

	public boolean insertHuman(Human human);

	public Human findHuman(String jumin);

	public boolean deleteHuman(String jumin);

	public void showAll();

}
