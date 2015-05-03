package ia;

import carte.Case.ObservationCase;
import agent.Action;

public interface Ia {
	public void observer(Integer[] positionActuelle,ObservationCase observationCaseActuelle);
	public Action deciderActionSuivante();
	public String parler();
}
